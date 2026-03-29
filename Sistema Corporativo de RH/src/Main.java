import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaERS sistemaERS = new SistemaERS();

        // Dados iniciais
        Colaborador colaborador1 = new Colaborador(12, "Vitor Alcantara", "Senior", 50000.00, true, LocalDate.now());
        Colaborador colaborador2 = new Colaborador(123, "Kaike Souza", "Junior", 15000.00, true, LocalDate.now());
        Colaborador colaborador3 = new Colaborador(1234, "Ana Clara", "Gerente", 60000.00, true, LocalDate.now());
        Colaborador colaborador4 = new Colaborador(12345, "Kauã Silva", "Desenvolvedor", 35000.00, true, LocalDate.now());
        Colaborador colaborador5 = new Colaborador(123456, "Vitor Fernandes", "Vendas", 40000.00, true, LocalDate.now());

        Recurso recurso1 = new Recurso(1, "Notebook", "Tecnologia", true, 2000.0);
        Recurso recurso2 = new Recurso(2, "Iphone", "Tecnologia", true, 5550.0);
        Recurso recurso3 = new Recurso(3, "Mouse", "Tecnologia", true, 100.0);

        sistemaERS.cadastrarColaborador(colaborador1);
        sistemaERS.cadastrarColaborador(colaborador2);
        sistemaERS.cadastrarColaborador(colaborador3);
        sistemaERS.cadastrarColaborador(colaborador4);
        sistemaERS.cadastrarColaborador(colaborador5);

        sistemaERS.cadastrarRecurso(recurso1);
        sistemaERS.cadastrarRecurso(recurso2);
        sistemaERS.cadastrarRecurso(recurso3);

        int opcao;

        do {
            System.out.println("\n===== MENU SISTEMA ERS =====");
            System.out.println("1 - Cadastrar colaborador");
            System.out.println("2 - Cadastrar recurso");
            System.out.println("3 - Alocar recurso");
            System.out.println("4 - Devolver recurso");
            System.out.println("5 - Mostrar colaboradores");
            System.out.println("6 - Mostrar recursos");
            System.out.println("7 - Buscar colaborador pelo nome");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            switch (opcao) {
                case 1:
                    System.out.print("ID do colaborador: ");
                    int idColab = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();

                    System.out.print("Salário: ");
                    double salario = scanner.nextDouble();

                    System.out.print("Ativo? (true/false): ");
                    boolean ativo = scanner.nextBoolean();
                    scanner.nextLine();

                    Colaborador novoColaborador = new Colaborador(
                            idColab, nome, cargo, salario, ativo, LocalDate.now()
                    );

                    sistemaERS.cadastrarColaborador(novoColaborador);
                    System.out.println("Colaborador cadastrado com sucesso.");
                    break;

                case 2:
                    System.out.print("ID do recurso: ");
                    int idRecurso = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Nome do recurso: ");
                    String nomeRecurso = scanner.nextLine();

                    System.out.print("Categoria: ");
                    String categoria = scanner.nextLine();

                    System.out.print("Disponível? (true/false): ");
                    boolean disponivel = scanner.nextBoolean();

                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();

                    Recurso novoRecurso = new Recurso(
                            idRecurso, nomeRecurso, categoria, disponivel, valor
                    );

                    sistemaERS.cadastrarRecurso(novoRecurso);
                    System.out.println("Recurso cadastrado com sucesso.");
                    break;

                case 3:
                    System.out.print("ID do colaborador: ");
                    int colaboradorId = scanner.nextInt();

                    System.out.print("ID do recurso: ");
                    int recursoId = scanner.nextInt();
                    scanner.nextLine();

                    sistemaERS.alocarRecurso(colaboradorId, recursoId);
                    break;

                case 4:
                    System.out.print("ID do recurso a devolver: ");
                    int recursoDevolver = scanner.nextInt();
                    scanner.nextLine();

                    sistemaERS.devolverRecurso(recursoDevolver);
                    break;

                case 5:
                    sistemaERS.mostrarColaboradores();
                    break;

                case 6:
                    sistemaERS.mostrarRecursos();
                    break;

                case 7:
                    System.out.print("Digite o nome do colaborador: ");
                    String nomeBusca = scanner.nextLine();

                    sistemaERS.buscarColaboradorPeloNome(nomeBusca);
                    break;

                case 0:
                    System.out.println("Encerrando o sistema...");
                    break;

                default:
                    System.out.println("Opção inválida.");
            }

        } while (opcao != 0);

        scanner.close();
    }
}