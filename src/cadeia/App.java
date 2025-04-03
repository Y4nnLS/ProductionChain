package cadeia;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import cadeia.fabrica.LogFabrica;
import cadeia.modelo.TipoVeiculo;
import cadeia.modelo.Veiculo;
import cadeia.util.CorVeiculo;

public class App {
    private static final AtomicInteger geradorId = new AtomicInteger(1);
    private static final Random random = new Random();
    private static int estoque = 500;

    public static void main(String[] args) {
        System.out.println("Simulação de Produção e Venda de Veículos");

        while (estoque > 0) {
            int id = geradorId.getAndIncrement();

            CorVeiculo cor = CorVeiculo.alternar(random.nextInt(3));
            TipoVeiculo tipo = TipoVeiculo.alternar(random.nextInt(2));
            int estacao = random.nextInt(4) + 1;
            int funcionario = 101 + random.nextInt(20);
            int posEsteiraFabrica = random.nextInt(40);

            Veiculo v = new Veiculo(
                    id,
                    cor,
                    tipo,
                    estacao,
                    funcionario,
                    posEsteiraFabrica);

            LogFabrica.registrarProducao(v);

            int loja = random.nextInt(3) + 1;
            int posEsteiraLoja = random.nextInt(40);

            v.setLoja(loja, posEsteiraLoja);
            LogFabrica.registrarVenda(v);

            estoque--;

            try {
                Thread.sleep(10); // Simula tempo de produção
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Simulação encerrada. Verifique os arquivos de log.");
    }
}
