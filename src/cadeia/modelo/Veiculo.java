package cadeia.modelo;

import cadeia.util.CorVeiculo;

public class Veiculo {
    private final int id;
    private final CorVeiculo cor;
    private final TipoVeiculo tipo;
    private final int idEstacao;
    private final int idFuncionario;
    private final int posicaoEsteira;

    private int idLoja = -1;
    private int posicaoEsteiraLoja = -1;

    public Veiculo(int id, CorVeiculo cor, TipoVeiculo tipo, int idEstacao, int idFuncionario, int posicaoEsteira) {
        this.id = id;
        this.cor = cor;
        this.tipo = tipo;
        this.idEstacao = idEstacao;
        this.idFuncionario = idFuncionario;
        this.posicaoEsteira = posicaoEsteira;
    }

    public int getId() {
        return id;
    }

    public CorVeiculo getCor() {
        return cor;
    }

    public TipoVeiculo getTipo() {
        return tipo;
    }

    public int getIdEstacao() {
        return idEstacao;
    }

    public int getIdFuncionario() {
        return idFuncionario;
    }

    public int getPosicaoEsteira() {
        return posicaoEsteira;
    }

    public void setLoja(int idLoja, int posicaoEsteiraLoja) {
        this.idLoja = idLoja;
        this.posicaoEsteiraLoja = posicaoEsteiraLoja;
    }

    public int getIdLoja() {
        return idLoja;
    }

    public int getPosicaoEsteiraLoja() {
        return posicaoEsteiraLoja;
    }

    public String resumoProducao() {
        return String.format(
                "[ID=%d] %s %s | Estação: %d | Funcionário: %d | Posição Esteira: %d",
                id, tipo.getDescricao(), cor.getDescricao(), idEstacao, idFuncionario, posicaoEsteira);
    }

    public String resumoVenda() {
        return String.format(
                "%s | Loja: %d | Posição Esteira Loja: %d",
                resumoProducao(), idLoja, posicaoEsteiraLoja);
    }
}
