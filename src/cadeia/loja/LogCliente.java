package cadeia.loja;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogCliente {
    private static final String LOG_CAMINHO = "src/resources/logs/compras_clientes.log";
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void registrar(String clienteNome, String mensagem) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_CAMINHO, true))) {
            out.println("[" + dtf.format(LocalDateTime.now()) + "] " + clienteNome + " - " + mensagem);
        } catch (IOException e) {
            System.err.println("Erro ao gravar log cliente: " + e.getMessage());
        }
    }
}
