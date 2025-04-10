package cadeia;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import cadeia.fabrica.LogFabrica;
import cadeia.loja.Cliente;
import cadeia.loja.Garagem;
import cadeia.modelo.TipoVeiculo;
import cadeia.modelo.Veiculo;
import cadeia.util.CorVeiculo;

public class App {
    private static final AtomicInteger geradorId = new AtomicInteger(1);
    private static final Random random = new Random();
    private static final int ESTOQUE = 500;

    public static void main(String[] args) {
        System.out.println("Simulação de Produção e Venda de Veículos");

        Garagem garagem = new Garagem();

        // Inicia 20 clientes (threads)
        for (int i = 1; i <= 20; i++) {
            Cliente cliente = new Cliente("Cliente-" + i, garagem);
            cliente.start();
        }

        // Produção dos veículos
        for (int i = 0; i < ESTOQUE; i++) {
            int id = geradorId.getAndIncrement();
            CorVeiculo cor = CorVeiculo.alternar(random.nextInt(3));
            TipoVeiculo tipo = TipoVeiculo.alternar(random.nextInt(2));
            int estacao = random.nextInt(4) + 1;
            int funcionario = 101 + random.nextInt(20);
            int posEsteiraFabrica = random.nextInt(40);

            Veiculo v = new Veiculo(id, cor, tipo, estacao, funcionario, posEsteiraFabrica);
            LogFabrica.registrarProducao(v);

            int loja = random.nextInt(3) + 1;
            int posEsteiraLoja = random.nextInt(40);
            v.setLoja(loja, posEsteiraLoja);
            LogFabrica.registrarVenda(v);

            try {
                garagem.adicionar(v); // Envia veículo para a garagem
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulação encerrada. Verifique os arquivos de log.");
    }
}
