package cadeia.fabrica;

import cadeia.modelo.Veiculo;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class FabricaSocketServer {
    public static void main(String[] args) throws Exception {
        Fabrica fabrica = new Fabrica();
        fabrica.iniciarProducao();

        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("[Fábrica] Servidor socket iniciado na porta 5000...");

        while (true) {
            Socket clienteSocket = serverSocket.accept();
            System.out.println("[Fábrica] Loja conectada: " + clienteSocket.getInetAddress());

            new Thread(() -> {
                try (ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream())) {
                    while (true) {
                        for (var estacao : fabrica.getEstacoes()) {
                            var esteira = estacao.getEsteira();
                            if (esteira.getTamanho() > 0) {
                                Veiculo v = esteira.remover();
                                LogFabrica.registrarVenda(v);
                                out.writeObject(v);
                                out.flush();
                                System.out.println("[Fábrica] Enviado veículo: " + v.getId());
                            }
                        }
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                    System.out.println("[Fábrica] Loja desconectada.");
                }
            }).start();
        }
    }
}