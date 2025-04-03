package cadeia.fabrica;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import cadeia.modelo.Veiculo;

public class LogFabrica {

    private static final String LOG_PRODUCAO = "src/resources/logs/producao_fabrica.log";
    private static final String LOG_VENDA = "src/resources/logs/venda_fabrica.log";

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void registrarProducao(Veiculo veiculo) {
        registrar(LOG_PRODUCAO, "[PRODUZIDO] " + dtf.format(LocalDateTime.now()) + " - " + veiculo.resumoProducao());
    }

    public static synchronized void registrarVenda(Veiculo veiculo) {
        registrar(LOG_VENDA, "[VENDIDO]   " + dtf.format(LocalDateTime.now()) + " - " + veiculo.resumoVenda());
    }

    private static void registrar(String caminho, String mensagem) {
        try (PrintWriter out = new PrintWriter(new FileWriter(caminho, true))) {
            out.println(mensagem);
        } catch (IOException e) {
            System.err.println("Erro ao gravar log: " + e.getMessage());
        }
    }
}
