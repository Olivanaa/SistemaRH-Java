package service;

import enums.TipoMovimentacao;
import model.Alocacao;
import model.Colaborador;
import model.MovimentacaoRecurso;
import model.Recurso;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SistemaERS {
    //Lista de colaboradores X
    //Lista de recursos X
    //Lista de alocações X
    //Metodos de manipulacao (cadastrar v, alocar v, devolver v, buscar v, exibir v)

    DateTimeFormatter formatoDataHora = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private ArrayList<Colaborador> colaboradores = new ArrayList<>();
    private ArrayList<Recurso> recursos = new ArrayList<>();
    private ArrayList<Alocacao> alocacoes = new ArrayList<>();
    private ArrayList<MovimentacaoRecurso> historicoMovimentacoes = new ArrayList<>();

    public void cadastrarColaborador(Colaborador colaborador){
        colaboradores.add(colaborador);
        System.out.println("Colaborador adicionado com sucesso: " +
                "ID: " + colaborador.getId() +
                " | Nome: " + colaborador.getNome() +
                " | Cargo: " + colaborador.getCargo() +
                " | Salário: " + colaborador.getSalario() +
                (colaborador.isAtivo()? " | Ativo" : " | Inativo") +
                " | Data de Admissao: " + colaborador.getDataDeAdmissao().format(formatoData));
    }

    public void cadastrarRecurso(Recurso recurso){
        recursos.add(recurso);
        System.out.println("Recurso adicionado com sucesso: " +
                "ID: " + recurso.getId() +
                " | Nome: " + recurso.getNomeDoRecurso() +
                " | Categoria: " + recurso.getCategoria() +
                (recurso.isDisponivel()? " | Disponivel" : " | Indisponivel") +
                " | Valor: " + recurso.getValorEstimado());
    }

    public void alocarRecurso(int colaboradorId, int recursoId) {

        // cada colaborador
        for (Colaborador c : colaboradores){
            if (c.getId() == colaboradorId ) {
                // Se encontrar o colaborador segue o código

                // cada recurso
                for (Recurso r : recursos){

                    // Se encontrar o recurso segue o código
                    if (r.getId() == recursoId ){

                        //Condição para ser Alocado na classe Recurso
                        if(!r.podeSerAlocado()){
                            System.out.println("Recurso requer autorização especial).");
                            return;
                        } else if (!r.isDisponivel()) {
                            System.out.println("Recurso indisponivel");
                            return;
                        }

                        // Adiciona na lista alocações o new Alocacao
                        alocacoes.add(new Alocacao(c, r, LocalDateTime.now(), "Alugado"));

                        // Adiciona no histórico
                        historicoMovimentacoes.add(
                                new MovimentacaoRecurso(
                                        r,
                                        c,
                                        TipoMovimentacao.ALOCADO
                                )
                        );

                        // Mudar a disponibilidade para false
                        r.setDisponivel(false);

                        System.out.println("Recurso: "+ r.getNomeDoRecurso() + " alocado com sucesso!");
                        return;
                    }
                }
                System.out.println("Recurso não encontrado!");
                return;
            }
        }
        System.out.println("Colaborador não encontrado!");
    }

    public void devolverRecurso(int recursoId){

        Alocacao alocacaoEncontrada = null;
        // Encontrar alocacao
        for (Alocacao a : alocacoes) {
            if (a.getRecurso().getId() == recursoId){
                alocacaoEncontrada = a;
                break;
            }
        }

        if (alocacaoEncontrada == null){
            System.out.println("Recurso não foi alugado ainda ou não encontrado nas alocações.");
            return;
        }

        for (Recurso r : recursos){
            if (r.getId() == recursoId){
                r.setDisponivel(true);

                // Adiciona nos históricos
                historicoMovimentacoes.add(
                        new MovimentacaoRecurso(
                                alocacaoEncontrada.getRecurso(),
                                alocacaoEncontrada.getColaborador(),
                                TipoMovimentacao.DEVOLVIDO
                        )
                );
                break;
            }
        }

        // Remove de alocacoes
        alocacoes.remove(alocacaoEncontrada);

        System.out.println("Recurso devolvido com sucesso!");

    }

    public void mostrarRecursos(){
        if (recursos == null || recursos.isEmpty()) {
            System.out.println("Nenhuma recurso encontrado.");
            return;
        }

        System.out.println("Lista de recursos:");
        for (Recurso r : recursos) {
            String status = r.isDisponivel() ? "Disponível" : "Alocado";

            System.out.println(
                    "ID: " + r.getId() +
                            " | Nome: " + r.getNomeDoRecurso() +
                            " | Categoria: " + r.getCategoria() +
                            " | Status: " + status
            );
        }
    }

    public void mostrarColaboradores(){
        if (colaboradores == null || colaboradores.isEmpty()) {
            System.out.println("Nenhuma colaborador encontrado(a).");
            return;
        }

        System.out.println("Nossos colaboradores:");
        for (Colaborador c: colaboradores){
            System.out.println("ID: " + c.getId() +  " | Nome: "  + c.getNome() + " | Cargo: " + c.getCargo() + " | Salário: " + c.getSalario() + " | Data de Admissao: "  + c.getDataDeAdmissao().format(formatoData));

        }
    }


    public void exibirColaboradorPeloNome(String colaboradorNome){
        for (Colaborador c : colaboradores){
            String nome = c.getNome().toLowerCase();
            if (nome.equals(colaboradorNome.toLowerCase())){
                String status = c.isAtivo() ? "Ativo" : "Inativo";

                System.out.println("Colaborador encontrado: " + c.getNome() + " | Cargo: " + c.getCargo()+ " | " + status + " | Data de Admissao: " + c.getDataDeAdmissao().format(formatoData));
                return;
            }
        }
        System.out.println("Colaborador não encontrado.");
    }

    public Colaborador buscarColaboradorPeloNome(String nomeColaborador){
        for (Colaborador c : colaboradores){
            String nome = c.getNome().toLowerCase();
            if (nome.equals(nomeColaborador.toLowerCase())){
                return c;
            }
        }
        return null;
    }


    public void promoverColaborador(Colaborador c, String novoCargo, double novoSalario){
        c.promover(novoCargo, novoSalario);
        System.out.println("Colaborador promovido com sucesso!");
    }


    // Etapa de Inovação  |
    //                    V
    public void mostrarHistoricoAlocacao(int recursoId) {
        boolean encontrou = false;

        for (MovimentacaoRecurso m : historicoMovimentacoes) {
            if (m.getRecurso().getId() == recursoId) {
                System.out.println(
                        "Recurso: " + m.getRecurso().getNomeDoRecurso()
                                + " | Movimentação: " + m.getTipoMovimentacao()
                                + " | Colaborador: " + m.getColaborador().getNome()
                                + " | Data/Hora: " + m.getDataHora().format(formatoDataHora)
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum histórico encontrado para esse recurso.");
        }
    }
}