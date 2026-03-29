import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SistemaERS {
    //Lista de colaboradores X
    //Lista de recursos X
    //Lista de alocações X
    //Metodos de manipulacao (cadastrar v, alocar v, devolver v, buscar v, exibir v)

    Colaborador colaborador;
    Recurso recurso;

    LocalDateTime agora = LocalDateTime.now();

    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    String dataFormatada = agora.format(formato);

    ArrayList<Colaborador> colaboradores = new ArrayList<>();
    ArrayList<Recurso> recursos = new ArrayList<>();
    ArrayList<Alocacao> alocacoes = new ArrayList<>();
    ArrayList<MovimentacaoRecurso> historicoMovimentacoes = new ArrayList<>();

    public void cadastrarColaborador(Colaborador colaborador){
        Colaborador novoColaborador = new Colaborador( colaborador.id, colaborador.nome, colaborador.cargo, colaborador.salario, colaborador.ativo, colaborador.dataDeAdmissao);
        colaboradores.add(novoColaborador);
        System.out.println("Colaborador adicionado com sucesso: "+ novoColaborador.nome + " " + novoColaborador.cargo);
    }

    public void cadastrarRecurso(Recurso recurso){
        Recurso novoRecurso = new Recurso(recurso.getId(), recurso.getNomeDoRecurso(),  recurso.getCategoria(), recurso.isDisponivel(), recurso.getValorEstimado());
        recursos.add(novoRecurso);
        System.out.println("Recurso adicionado com sucesso: "+ recurso.getNomeDoRecurso());
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
                           System.out.println("Recurso não pode ser alocado (indisponível ou valor alto).");
                           return;
                       }

                       // Adiciona na lista alocações o new Alocacao
                       alocacoes.add(new Alocacao(colaboradorId, recursoId, null, "Alugado"));

                       // Adiciona no histórico
                       historicoMovimentacoes.add(
                               new MovimentacaoRecurso(
                                       r.getId(),
                                       r.getNomeDoRecurso(),
                                       "ALOCADO",
                                       colaborador.getNome(),
                                       dataFormatada
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
            if (a.getRecursoId() == recursoId){
                alocacaoEncontrada = a;
                return;
            }

            if (alocacaoEncontrada == null){
                System.out.println("Alocação não encontrada.");
                return;
            }

            for (Recurso r : recursos){
                if (r.getId() == recursoId){
                    r.setDisponivel(true);

                    // Adiciona nos históricos
                    historicoMovimentacoes.add(
                            new MovimentacaoRecurso(
                                    r.getId(),
                                    r.getNomeDoRecurso(),
                                    "DEVOLVIDO",
                                    "N/A",
                                    dataFormatada
                            )
                    );
                    break;
                }
            }

            // Remove de alocacoes
            alocacoes.remove(alocacaoEncontrada);

            System.out.println("Recurso devolvido com sucesso!");

        }
    }

    public void mostrarRecursos(){
        if (recursos == null || recursos.isEmpty()) {
            System.out.println("Nenhuma recurso encontrado.");
            return;
        }

        System.out.println("Itens alocados:");
        for (Recurso r: recursos){
            System.out.println(r.id + ", " + r.nomeDoRecurso + ", categoria: " + r.categoria + ", disponibilidade: " + r.disponivel);
        }
    }

    public void mostrarColaboradores(){
        if (colaboradores == null || colaboradores.isEmpty()) {
            System.out.println("Nenhuma colaborador encontrado(a).");
            return;
        }

        System.out.println("Nossos colaboradores:");
        for (Colaborador c: colaboradores){
            System.out.println(c.id + ", " + c.nome + ", cargo: " + c.cargo + ", salário: " + c.salario + ", Data de Admissão: " + c.dataDeAdmissao);
        }
    }

    public void buscarColaboradorPeloNome(String colaboradorNome ){
        for (Colaborador c : colaboradores){
            String nome = c.nome;
            if (nome.equals(colaboradorNome)){
                System.out.println("Colaborador encontrado: " + c.nome + ", " + c.cargo+ ", " + c.ativo + ", " + c.dataDeAdmissao);
                return;
            }
        }
        System.out.println("Colaborador não encontrado.");
    }

    public void promoverColaborador(String colaboradorNome, String novoCargo, double novoSalario){
        for (Colaborador c : colaboradores){
            if (c.getNome().equals(colaboradorNome)){
                c.promover(novoCargo, novoSalario);
                System.out.println("Colaborar promovido com sucesso!");
                return;
            }
        }
        System.out.println("Colaborador: "+ colaborador.getNome() + " não encontrado!");
    }


    // Etapa de Inovação  |
    //                    V
    public void mostrarHistoricoAlocacao(int recursoId) {
        boolean encontrou = false;

        for (MovimentacaoRecurso m : historicoMovimentacoes) {
            if (m.getRecursoId() == recursoId) {
                System.out.println(
                        "Recurso: " + m.getNomeRecurso()
                                + " | Movimentação: " + m.getTipoMovimentacao()
                                + " | Colaborador: " + m.getColaboradorNome()
                                + " | Data/Hora: " + m.getDataHora()
                );
                encontrou = true;
            }
        }

        if (!encontrou) {
            System.out.println("Nenhum histórico encontrado para esse recurso.");
        }
    }
}