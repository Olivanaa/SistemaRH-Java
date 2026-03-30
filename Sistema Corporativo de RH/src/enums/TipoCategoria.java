package enums;

public enum TipoCategoria {
    TECNOLOGIA("Tecnologia"),
    MOBILIA("Mobília"),
    ESCRITORIO("Escritório"),
    CELULARES("Celulares"),
    SEGURANCA("Segurança");

    private String descricao;

    TipoCategoria(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}



