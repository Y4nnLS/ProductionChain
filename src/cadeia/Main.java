import java.util.Random;

public class Main {
    public static void main(String[] args) {
        EsteiraCircular<String> esteira = new EsteiraCircular<>(5); // Capacidade pequena pra testar

        // Thread de produtor (simula funcionários colocando veículos)
        Runnable produtor = () -> {
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                String item = "Item-" + i;
                try {
                    int pos = esteira.inserir(item);
                    System.out.println("[PRODUTOR] Inseriu: " + item + " na posição " + pos);
                    Thread.sleep(rand.nextInt(500)); // Simula tempo de produção
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Thread de consumidor (simula a fábrica ou loja pegando veículos)
        Runnable consumidor = () -> {
            Random rand = new Random();
            for (int i = 0; i < 10; i++) {
                try {
                    String item = esteira.remover();
                    System.out.println("    [CONSUMIDOR] Removeu: " + item);
                    Thread.sleep(rand.nextInt(1000)); // Simula tempo de envio ou uso
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        };

        // Criar e iniciar as threads
        Thread t1 = new Thread(produtor);
        Thread t2 = new Thread(consumidor);

        t1.start();
        t2.start();

        // Espera terminar
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n[FINALIZADO] Teste de esteira circular completo.");
    }
}
