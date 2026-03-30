package enums;

public enum TipoMovimentacao {
    ALOCADO("Alocado"),
    DEVOLVIDO("Devolvido");

    private String descricao;

    TipoMovimentacao(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }

}
