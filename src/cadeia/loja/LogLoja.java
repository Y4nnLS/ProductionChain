package cadeia.loja;

import cadeia.modelo.Veiculo;
import java.io.FileWriter;
import java.io.IOException;
import static java.time.LocalDateTime.now;

public class LogLoja {
    private final String logEntrada;

    public LogLoja(String nomeLoja) {
        this.logEntrada = "log_entrada_" + nomeLoja + ".log";
    }

    public synchronized void registrarEntrada(Veiculo veiculo, String nomeLoja, int posicaoEsteira) {
        try (FileWriter writer = new FileWriter(logEntrada, true)) {
            writer.write(String.format("[Loja %s] [%s] Veiculo ID: %d, Cor: %s, Tipo: %s, Estacao: %d, Funcionario: %d, Posicao na esteira: %d\n",
                nomeLoja,
                now(),
                veiculo.getId(),
                veiculo.getCor(),
                veiculo.getTipo(),
                veiculo.getIdEstacao(),
                veiculo.getIdFuncionario(),
                posicaoEsteira
            ));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}