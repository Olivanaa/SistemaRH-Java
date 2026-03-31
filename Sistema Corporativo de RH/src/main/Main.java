package main;

import enums.TipoCategoria;
import model.Colaborador;
import model.Recurso;
import service.SistemaERS;
import ui.ExibirMenu;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaERS sistemaERS = new SistemaERS();

        // Dados iniciais
        Colaborador colaborador1 = new Colaborador("Vitor Alcantara", "Senior", 50000.00,LocalDate.now());
        Colaborador colaborador2 = new Colaborador("Kaike Souza", "Junior", 15000.00,LocalDate.now());
        Colaborador colaborador3 = new Colaborador("Ana Clara", "Gerente", 60000.00, LocalDate.now());
        Colaborador colaborador4 = new Colaborador( "Kauã Silva", "Desenvolvedor", 35000.00,LocalDate.now());
        Colaborador colaborador5 = new Colaborador("Vitor Fernandes", "Vendas", 40000.00,LocalDate.now());

        Recurso recurso1 = new Recurso("Notebook", TipoCategoria.TECNOLOGIA, 2000.0);
        Recurso recurso2 = new Recurso("Iphone", TipoCategoria.CELULARES, 5550.0);
        Recurso recurso3 = new Recurso("Mouse", TipoCategoria.TECNOLOGIA, 100.0);

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
            ExibirMenu.exibirMenu();

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Digite apenas números.");
                opcao = -1;
                continue;
            }

            switch (opcao) {
                case 1:

                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();

                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();

                    System.out.print("Salário: ");
                    double salario = Double.parseDouble(scanner.nextLine());

                    Colaborador novoColaborador = new Colaborador(
                            nome, cargo, salario, LocalDate.now()
                    );

                    sistemaERS.cadastrarColaborador(novoColaborador);
                    break;

                case 2:
                    System.out.print("Nome do recurso: ");
                    String nomeRecurso = scanner.nextLine();

                    TipoCategoria categoria = null;
                    boolean voltarMenu = false;


                    while (categoria == null) {
                        ExibirMenu.categoriaMenu();
                        try {
                            int opcaoCategoria = Integer.parseInt(scanner.nextLine());

                            switch (opcaoCategoria) {
                                case 1:
                                    categoria = TipoCategoria.TECNOLOGIA;
                                    break;
                                case 2:
                                    categoria = TipoCategoria.MOBILIA;
                                    break;
                                case 3:
                                    categoria = TipoCategoria.ESCRITORIO;
                                    break;
                                case 4:
                                    categoria = TipoCategoria.CELULARES;
                                    break;
                                case 5:
                                    categoria = TipoCategoria.SEGURANCA;
                                    break;
                                case 0:
                                    voltarMenu = true;
                                    break;
                                default:
                                    System.out.println("Categoria inválida.");
                            }

                            if (voltarMenu) {
                                break;
                            }

                        } catch (NumberFormatException e) {
                            System.out.println("Digite apenas números.");
                        }
                    }

                    if (voltarMenu) {
                        break;
                    }

                    System.out.print("Valor: ");
                    double valor = Double.parseDouble(scanner.nextLine());

                    Recurso novoRecurso = new Recurso(nomeRecurso, categoria, valor);

                    sistemaERS.cadastrarRecurso(novoRecurso);
                    break;

                case 3:
                    sistemaERS.mostrarColaboradores();

                    int colaboradorId = -1; // Inicializa com um valor inválido

                    while (colaboradorId == -1) {
                        System.out.print("ID do colaborador: ");

                        try {
                            colaboradorId = Integer.parseInt(scanner.nextLine()); // Tenta converter a entrada
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                        }
                    }

                    sistemaERS.mostrarRecursos();

                    int recursoId = -1; // Inicializa com um valor inválido
                    while (recursoId == -1) {
                        System.out.print("ID do recurso: ");
                        try {
                            recursoId = Integer.parseInt(scanner.nextLine()); // Tenta converter a entrada
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                        }
                    }

                    sistemaERS.alocarRecurso(colaboradorId, recursoId);
                    break;

                case 4:
                    sistemaERS.mostrarRecursos();

                    int recursoC = -1;
                    while (recursoC == -1) {
                        System.out.print("ID do recurso a devolver: ");
                        try {
                            recursoC = Integer.parseInt(scanner.nextLine());
                        } catch (NumberFormatException e) {
                            System.out.println("Entrada inválida! Digite apenas números.");
                        }
                    }
                    sistemaERS.devolverRecurso(recursoC);
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

                    sistemaERS.exibirColaboradorPeloNome(nomeBusca);
                    break;
                case 8:
                    sistemaERS.mostrarRecursos();

                    System.out.println("Digite o id do recurso: ");
                    int idRecurso = scanner.nextInt();
                    scanner.nextLine();
                    sistemaERS.mostrarHistoricoAlocacao(idRecurso);
                    break;
                case 9:
                    System.out.print("Digite o nome do colaborador: ");
                    String nomeColaborador = scanner.nextLine();

                    Colaborador c = sistemaERS.buscarColaboradorPeloNome(nomeColaborador);

                    if (c == null) {
                        System.out.println("Colaborador não encontrado.");
                        break;
                    }

                    System.out.println("Colaborador encontrado: " +
                            c.getNome() + " | Cargo atual: " + c.getCargo() + " | Salario atual: " + c.getSalario());

                    System.out.print("Novo cargo: ");
                    String novoCargo = scanner.nextLine();

                    System.out.print("Novo salário: ");
                    double novoSalario = Double.parseDouble(scanner.nextLine());

                    sistemaERS.promoverColaborador(c, novoCargo, novoSalario);
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