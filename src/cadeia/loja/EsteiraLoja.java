package cadeia.loja;


import cadeia.modelo.Veiculo;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


class EsteiraLoja {
    private final BlockingQueue<Veiculo> esteira;

    public EsteiraLoja(int capacidade) {
        this.esteira = new ArrayBlockingQueue<>(capacidade);
    }

    public void adicionarVeiculo(Veiculo veiculo) throws InterruptedException {
        esteira.put(veiculo);
    }

    public Veiculo retirarVeiculo() throws InterruptedException {
        return esteira.take();
    }
/* 
    public int getPosicao(Veiculo veiculo) {
        return ((ArrayBlockingQueue<Veiculo>) esteira).indexOf(veiculo);
    }*/

    public int getPosicao(Veiculo veiculo) {
        return new ArrayList<>(esteira).indexOf(veiculo);
    }

    public boolean estaVazia() {
        return esteira.isEmpty();
    }
}