package model;

import java.time.LocalDate;

public class Colaborador {

    // Um colaborador recém-admitido sempre começa como ativo = true.

    private int id;
    private String nome;
    private String cargo;
    private double salario;
    private boolean ativo;
    private LocalDate dataDeAdmissao;

    private static int proximoId = 1;

    public Colaborador (String nome, String cargo, double salario, LocalDate dataDeAdmissao){
        this.id = proximoId++;
        this.nome = nome;
        this.cargo = cargo;
        this.salario = salario;
        this.ativo = true;
        this.dataDeAdmissao = dataDeAdmissao;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCargo() {
        return cargo;
    }

    public double getSalario() {
        return salario;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDate getDataDeAdmissao() {
        return dataDeAdmissao;
    }

    public void promover(String novoCargo, double novoSalario) {
        this.cargo = novoCargo;
        this.salario = novoSalario;
    }

}
