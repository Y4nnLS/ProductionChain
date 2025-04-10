package cadeia;

import cadeia.fabrica.EstacaoProdutora;
import cadeia.fabrica.Fabrica;
import cadeia.loja.Cliente;
import cadeia.loja.Garagem;
import cadeia.loja.Loja;
import cadeia.modelo.Veiculo;
import cadeia.fabrica.LogFabrica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class teste {
    private static final String LOG_TERMINAL = "src/resources/logs/execution_output.log";

    public static void main(String[] args) throws InterruptedException {
        try (PrintWriter logger = new PrintWriter(new FileWriter(LOG_TERMINAL, true))) {
            Fabrica fabrica = new Fabrica();
            fabrica.iniciarProducao();

            Loja loja1 = new Loja("Loja1", 40);
            Loja loja2 = new Loja("Loja2", 40);
            Loja loja3 = new Loja("Loja3", 40);

            loja1.start();
            loja2.start();
            loja3.start();

            List<Loja> lojas = List.of(loja1, loja2, loja3);

            Garagem garagemCompartilhada = new Garagem();

            Thread distribuidor = new Thread(() -> {
                Random rand = new Random();
                while (true) {
                    try {
                        for (EstacaoProdutora estacao : fabrica.getEstacoes()) {
                            var esteira = estacao.getEsteira();
                            if (esteira.getTamanho() > 0) {
                                Veiculo v = esteira.remover();
                                int lojaIndex = rand.nextInt(lojas.size());
                                Loja lojaDestino = lojas.get(lojaIndex);
                                lojaDestino.receberVeiculo(v);
                                v.setLoja(lojaIndex, lojaDestino.temVeiculos() ? 1 : 0);
                                LogFabrica.registrarVenda(v);
                                garagemCompartilhada.adicionar(v);

                                logger.println("[INFO] Veículo distribuído para " + lojaDestino.getNome() + " - ID: "
                                        + v.getId());
                            }
                        }
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        logger.println("[ERROR] Distribuidor interrompido.");
                        break;
                    } catch (Exception e) {
                        logger.println("[ERROR] " + e.getMessage());
                    }
                }
            });
            distribuidor.start();

            List<Cliente> clientes = new ArrayList<>();
            for (int i = 0; i < 20; i++) {
                Cliente cliente = new Cliente("Cliente" + i, garagemCompartilhada);
                cliente.start();
                clientes.add(cliente);

                logger.println("[INFO] Cliente" + i + " iniciado.");
            }
        } catch (IOException e) {
            System.err.println("[FATAL] Falha ao criar log de execução: " + e.getMessage());
        }
    }
}
