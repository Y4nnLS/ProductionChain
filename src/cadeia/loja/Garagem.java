package cadeia.loja;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import cadeia.modelo.Veiculo;

public class Garagem {
    private final BlockingQueue<Veiculo> veiculos = new LinkedBlockingQueue<>();

    public void adicionar(Veiculo v) throws InterruptedException {
        veiculos.put(v);
    }

    public Veiculo retirar() throws InterruptedException {
        return veiculos.take();
    }

    public int tamanho() {
        return veiculos.size();
    }
}
