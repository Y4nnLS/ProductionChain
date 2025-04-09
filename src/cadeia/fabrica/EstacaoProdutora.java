package cadeia.fabrica;

import cadeia.modelo.Veiculo;
import cadeia.util.EsteiraCircular;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class EstacaoProdutora {

    private final int idEstacao;
    private final Fabrica fabrica;
    private final EsteiraCircular<Veiculo> esteira;
    private final List<Funcionario> funcionarios = new ArrayList<>();
    private final Lock[] ferramentas = new ReentrantLock[5];

    public EstacaoProdutora(int idEstacao, Fabrica fabrica) {
        this.idEstacao = idEstacao;
        this.fabrica = fabrica;
        this.esteira = new EsteiraCircular<>(40);

        inicializarFerramentas();
        inicializarFuncionarios();
    }

    public int getIdEstacao() {
        return idEstacao;
    }

    private void inicializarFerramentas() {
        for (int i = 0; i < ferramentas.length; i++) {
            ferramentas[i] = new ReentrantLock();
        }
    }

    private void inicializarFuncionarios() {
        for (int i = 0; i < 5; i++) {
            Lock esquerda = ferramentas[i];
            Lock direita = ferramentas[(i + 1) % 5];

            Funcionario f = new Funcionario(i, this, esquerda, direita);
            funcionarios.add(f);
        }
    }

    public void iniciarFuncionarios() {
        for (Funcionario f : funcionarios) {
            f.start();
        }
    }

    public Veiculo produzirVeiculo(Funcionario funcionario) throws InterruptedException {
        Veiculo v = fabrica.produzirVeiculo(idEstacao, funcionario.getIdFuncionario());

        int posicao = esteira.adicionar(v);

        System.out.printf("Veículo %d produzido por funcionário %d na estação %d (posição %d da esteira)%n", v.getId(), funcionario.getIdFuncionario(), idEstacao, posicao);

        return v;
    }

    public EsteiraCircular<Veiculo> getEsteira() {
        return esteira;
    }

}
