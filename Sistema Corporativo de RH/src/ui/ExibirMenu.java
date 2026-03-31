package ui;

import enums.TipoCategoria;

public class ExibirMenu {
    public static void exibirMenu() {
        System.out.println("\n===== MENU SISTEMA ERS =====");
        System.out.println("1 - Cadastrar colaborador");
        System.out.println("2 - Cadastrar recurso");
        System.out.println("3 - Alocar recurso");
        System.out.println("4 - Devolver recurso");
        System.out.println("5 - Mostrar colaboradores");
        System.out.println("6 - Mostrar recursos");
        System.out.println("7 - Buscar colaborador pelo nome");
        System.out.println("8 - Mostrar histórico de recurso");
        System.out.println("9 - Promover Colaborador");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }

    static TipoCategoria[] categorias = TipoCategoria.values();

    public static void categoriaMenu(){
        for (int i = 0; i < categorias.length; i++) {
            System.out.println((i + 1) + " - " + categorias[i].getDescricao());
        }
        System.out.println("0 - Voltar ao menu principal");
        System.out.print("Escolha a categoria: ");

    }
}