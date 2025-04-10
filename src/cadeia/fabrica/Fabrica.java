package cadeia.fabrica;

import cadeia.modelo.*;
import cadeia.util.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Fabrica {
    private final int LIMITE_ESTOQUE = 500;
    private int estoqueAtual = LIMITE_ESTOQUE;

    private final List<EstacaoProdutora> estacoes = new ArrayList<>();

    private final AtomicInteger contadorIdVeiculo = new AtomicInteger(0);

    private final List<CorVeiculo> cores = Arrays.asList(CorVeiculo.values());
    private final List<TipoVeiculo> tipos = Arrays.asList(TipoVeiculo.values());

    private int indexCor = 0;
    private int indexTipo = 0;

    public Fabrica() {
        for (int i = 0; i < 4; i++) {
            EstacaoProdutora estacao = new EstacaoProdutora(i, this);
            estacoes.add(estacao);
        }
    }

    public void iniciarProducao() {
        for (EstacaoProdutora estacao : estacoes) {
            estacao.iniciarFuncionarios();
        }
    }

    public synchronized Veiculo produzirVeiculo(int idEstacao, int idFuncionario) {
        if (estoqueAtual <= 0) {
            return null;
        }

        estoqueAtual--;

        int idVeiculo = contadorIdVeiculo.incrementAndGet();

        CorVeiculo cor = cores.get(indexCor);
        indexCor = (indexCor + 1) % cores.size();

        TipoVeiculo tipo = tipos.get(indexTipo);
        indexTipo = (indexTipo + 1) % tipos.size();

        Veiculo veiculo = new Veiculo(idVeiculo, cor, tipo, idEstacao, idFuncionario);

        System.out.println(veiculo.resumoProducao());
        return veiculo;
    }

    public List<EstacaoProdutora> getEstacoes() {
        return estacoes;
    }
}
