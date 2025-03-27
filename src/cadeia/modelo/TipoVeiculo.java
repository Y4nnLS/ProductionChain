package cadeia.modelo;

public enum TipoVeiculo {
    SUV(0, "Utilitário Esportivo"),
    SEDAN(1, "Sedan Tradicional");

    private final int codigo;
    private final String descricao;

    TipoVeiculo(int codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public static TipoVeiculo alternar(int index) {
        return values()[index % values().length];
    }

    public static TipoVeiculo fromCodigo(int codigo) {
        for (TipoVeiculo tipo : values()) {
            if (tipo.codigo == codigo) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Código inválido: " + codigo);
    }
}
