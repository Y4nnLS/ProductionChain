package cadeia;

import cadeia.fabrica.Fabrica;

public class Main {
    public static void main(String[] args) {
        System.out.println("🏭 Iniciando a FÁBRICA DE VEÍCULOS...");

        // Cria a fábrica com estações e funcionários
        Fabrica fabrica = new Fabrica();

        // Inicia a produção (funcionários começam a trabalhar)
        fabrica.iniciarProducao();

        // Deixa a produção rodar por um tempo
        try {
            Thread.sleep(120000); // 120 segundos de simulação
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println("\n🛑 Encerrando simulação da produção!");
        System.exit(0); // Finaliza o programa (mata threads de funcionários)
    }
}
