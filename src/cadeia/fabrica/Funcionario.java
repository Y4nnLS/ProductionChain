package cadeia.fabrica;

import java.util.concurrent.locks.Lock;

public class Funcionario extends Thread {
    private final int id;
    private final EstacaoProdutora estacao;
    private final Lock ferramentaEsquerda;
    private final Lock ferramentaDireita;

    public Funcionario(int id, EstacaoProdutora estacao, Lock esquerda, Lock direita) {
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
                } catch(InterruptedException e){
                    Thread.currentThread().interrupt();
                    System.out.println("Funcionário " + id + " interrompido durante a produção.");
                } 
                finally {
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
