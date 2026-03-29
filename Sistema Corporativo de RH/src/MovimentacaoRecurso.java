public class MovimentacaoRecurso {
    private int recursoId;
    private String nomeRecurso;
    private String tipoMovimentacao;
    private String colaboradorNome;
    private String dataHora;

    public MovimentacaoRecurso(int recursoId, String nomeRecurso, String tipoMovimentacao, String colaboradorNome, String dataHora) {
        this.recursoId = recursoId;
        this.nomeRecurso = nomeRecurso;
        this.tipoMovimentacao = tipoMovimentacao;
        this.colaboradorNome = colaboradorNome;
        this.dataHora = dataHora;
    }

    public int getRecursoId() {
        return recursoId;
    }

    public String getNomeRecurso() {
        return nomeRecurso;
    }

    public String getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public String getColaboradorNome() {
        return colaboradorNome;
    }

    public String getDataHora() {
        return dataHora;
    }
}
