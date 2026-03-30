package model;

import java.time.LocalDateTime;

public class Alocacao {

    // Um recurso só pode ser alocado se estiver disponivel == true.

    private Colaborador colaborador;
    private Recurso recurso;;
    private LocalDateTime data;
    private String observacao;

    public Alocacao(Colaborador colaborador, Recurso recurso, LocalDateTime data, String observacao) {
        this.colaborador = colaborador;
        this.recurso = recurso;
        this.data = data;
        this.observacao = observacao;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getObservacao() {
        return observacao;
    }
}
