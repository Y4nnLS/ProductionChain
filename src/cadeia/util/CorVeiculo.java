package cadeia.util;

public enum CorVeiculo {
    RED(0, "Vermelho"),
    GREEN(1, "Verde"),
    BLUE(2, "Azul");

    private final int codigo;
    private final String descricao;

    CorVeiculo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static CorVeiculo alternar(int index) {
        return values()[index % values().length];
    }

    public static CorVeiculo fromCodigo(int codigo) {
        for (CorVeiculo cor : values()) {
            if (cor.codigo == codigo) {
                return cor;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
