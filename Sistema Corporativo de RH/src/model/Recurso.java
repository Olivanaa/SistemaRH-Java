package model;

import enums.TipoCategoria;

public class Recurso {

    // só pode ser alocado se tiver disponivel

    private int id;
    private String nomeDoRecurso;
    private TipoCategoria categoria;
    private boolean disponivel;
    private double valorEstimado;

    private static int proximoId = 1;

    public Recurso(String nomeDoRecurso, TipoCategoria categoria, double valorEstimado) {
        this.id = proximoId++;
        this.nomeDoRecurso = nomeDoRecurso;
        this.categoria = categoria;
        this.disponivel = true;
        this.valorEstimado = valorEstimado;
    }

    public int getId() {
        return id;
    }

    public String getNomeDoRecurso() {
        return nomeDoRecurso;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public TipoCategoria getCategoria() {
        return categoria;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public double getValorEstimado() {
        return valorEstimado;
    }

    public boolean podeSerAlocado() {

        return valorEstimado <= 5000;
    }
}
