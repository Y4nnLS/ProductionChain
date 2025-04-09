// Funcionario.java
import java.util.concurrent.locks.ReentrantLock;

public class Funcionario extends Thread {
    private final int id;
    private final EstacaoProdutora estacao;
    private final ReentrantLock ferramentaEsquerda;
    private final ReentrantLock ferramentaDireita;

    public Funcionario(int id, EstacaoProdutora estacao, ReentrantLock esquerda, ReentrantLock direita) {
        this.id = id;
        this.estacao = estacao;
        this.ferramentaEsquerda = esquerda;
        this.ferramentaDireita = direita;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Tentar pegar ferramentas
                ferramentaEsquerda.lock();
                ferramentaDireita.lock();

                try {
                    estacao.produzirVeiculo(this);
                } finally {
                    // Liberar ferramentas
                    ferramentaDireita.unlock();
                    ferramentaEsquerda.unlock();
                }

                Thread.sleep((long) (500 + Math.random() * 500)); // Simula tempo de produção
            }
        } catch (InterruptedException e) {
            System.out.println("Funcionário " + id + " interrompido.");
        }
    }

    public int getIdFuncionario() {
        return id;
    }
}
