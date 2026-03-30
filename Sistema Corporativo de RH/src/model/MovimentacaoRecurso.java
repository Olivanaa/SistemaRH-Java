package model;

import enums.TipoMovimentacao;

import java.time.LocalDateTime;

public class MovimentacaoRecurso {
    private Recurso recurso;
    private Colaborador colaborador;
    private TipoMovimentacao tipoMovimentacao;
    private LocalDateTime dataHora;

    public MovimentacaoRecurso(Recurso recurso, Colaborador colaborador, TipoMovimentacao tipoMovimentacao) {
        this.recurso = recurso;
        this.colaborador = colaborador;
        this.tipoMovimentacao = tipoMovimentacao;
        this.dataHora = LocalDateTime.now();
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public Colaborador getColaborador() {
        return colaborador;
    }

    public TipoMovimentacao getTipoMovimentacao() {
        return tipoMovimentacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }
}