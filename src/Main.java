import carrinho.Carrinho;
import item.Item;
import item.Tipo;
import pagamento.InternacionalPagamentoFactory;
import pagamento.PagamentosFactoryInterface;
import pagamento.PixInterface;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static Carrinho carrinho = new Carrinho();
    private static List<Item> catalogoItens = new ArrayList<>();

    public static void main(String[] args) {
        PagamentosFactoryInterface internacionalFactory= new InternacionalPagamentoFactory();
        PixInterface internacionalPix= internacionalFactory.createPix();

        internacionalPix.pagar(200.0);

        inicializarCatalogo();
        exibirMenuPrincipal();
    }

    private static void inicializarCatalogo() {
        try {
            Path caminho = Paths.get("C:\\Users\\alyss\\OneDrive\\Desktop\\Faculdade\\Programação 3\\E-commerce\\src\\arquivos\\catalogo.txt");
            List<String> linhas = Files.readAllLines(caminho);

            for (String linha : linhas) {
                String[] dados = linha.split(";");

                int id = Integer.parseInt(dados[0]);
                String nome = dados[1];
                double preco = Double.parseDouble(dados[2]);
                Tipo tipo = Tipo.valueOf(dados[3]);

                catalogoItens.add(new Item(id, nome, preco, tipo));
            }
        } catch (Exception e) {
            System.out.println("Erro ao carregar catálogo: " + e.getMessage());
        }
    }

    public static void exibirMenuPrincipal() {

        int opcao = -1;

        while (opcao != 0) {

            System.out.println("=".repeat(50));
            System.out.println("SISTEMA DE E-COMMERCE");
            System.out.println("=".repeat(50));
            System.out.println("1. Ver Catálogo de Produtos");
            System.out.println("2. Adicionar Produto ao Carrinho");
            System.out.println("3. Ver Carrinho");
            System.out.println("4. Remover Produto do Carrinho");
            System.out.println("5. Fazer Pedido");
            System.out.println("6. Limpar Carrinho");
            System.out.println("0. Sair");
            System.out.println("=".repeat(50));
            System.out.print("Escolha uma opção: ");

            try {

                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        exibirCatalogo();
                        break;
                    case 2:
                        adicionarProdutoAoCarrinho();
                        break;
                    case 3:
                        exibirCarrinho();
                        break;
                    case 4:
                        removerProdutoDoCarrinho();
                        break;
                    case 5:
                        fazerPedido();
                        break;
                    case 6:
                        limparCarrinho();
                        break;
                    case 0:
                        System.out.println("\nObrigado por usar o sistema! Até logo!");
                        break;
                    default:
                        System.out.println("\nOpção inválida! Tente novamente.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Erro: Digite um número válido!");
                opcao = -1;
            }
            catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }

            if (opcao != 0) {
                System.out.println("Pressione ENTER para continuar...");
                scanner.nextLine();
            }
        }

        scanner.close();

    }

    public static void exibirCatalogo() {
        System.out.println("\n");
        System.out.println("=".repeat(70));
        System.out.println("CATÁLOGO DE PRODUTOS");
        System.out.println("=".repeat(70));

        System.out.printf("%-5s %-35s %-12s %-10s%n", "ID", "Produto", "Preço (R$)", "Tipo");
        System.out.println("-".repeat(70));

        for (Item item : catalogoItens) {
            System.out.printf("%-5d %-35s %-12.2f %-10s%n",
                    item.id,
                    item.nome,
                    item.valor,
                    item.tipo);
        }

        System.out.println("-".repeat(70));
    }

    public static void adicionarProdutoAoCarrinho() {
        System.out.println("\n");
        System.out.println("=".repeat(50));
        System.out.println("ADICIONAR PRODUTO AO CARRINHO");
        System.out.println("=".repeat(50));

        exibirCatalogo();

        System.out.print("\nDigite o ID do produto que deseja adicionar (0 para voltar): ");
        int id = Integer.parseInt(scanner.nextLine());

        if (id == 0) {
            return;
        }

        Item itemSelecionado = null;
        for (Item item : catalogoItens) {
            if (item.id == id) {
                itemSelecionado = item;
                break;
            }
        }

        if (itemSelecionado == null) {
            System.out.println("Produto não encontrado");
            return;
        }

        itemSelecionado.quantidade = 1;
        carrinho.adicionarItem(itemSelecionado);

        System.out.println("Produto adicionado ao carrinho com sucesso");
    }

    private static void exibirCarrinho() {
        System.out.println("\n");
        System.out.println("=".repeat(70));
        System.out.println("CARRINHO");
        System.out.println("=".repeat(70));

        if (carrinho.getItens().isEmpty()) {
            System.out.println("Carrinho vazio.");
            return;
        }

        System.out.printf("%-5s %-35s %-10s %-12s %-10s%n",
                "ID", "Produto", "Qtd", "Preço (R$)", "Tipo");
        System.out.println("-".repeat(70));

        double total = 0;

        for (Item item : carrinho.getItens()) {
            System.out.printf("%-5d %-35s %-10d %-12.2f %-10s%n",
                    item.id,
                    item.nome,
                    item.quantidade,
                    item.valor,
                    item.tipo);

            total += item.valor * item.quantidade;
        }

        System.out.println("-".repeat(70));
        System.out.printf("Valor total: R$ %.2f%n", total);
    }

    private static void removerProdutoDoCarrinho() {

        System.out.println("\n");
        System.out.println("=".repeat(50));
        System.out.println("REMOVER PRODUTO DO CARRINHO");
        System.out.println("=".repeat(50));

        exibirCarrinho();

        System.out.print("\nDigite o ID do produto que deseja remover (0 para voltar): ");
        int id = Integer.parseInt(scanner.nextLine());

        if (id == 0) {
            return;
        }

        carrinho.removerItem(id);
        System.out.println("Produto removido do carrinho com sucesso");

    }

    private static void fazerPedido() {
        System.out.println("Fazer pedido");
    }

    private static void limparCarrinho() {
        System.out.println("\n");
        System.out.println("=".repeat(50));
        System.out.println("LIMPAR CARRINHO");
        System.out.println("=".repeat(50));

        System.out.println("Deseja limpar o carrinho? S/N");

        char escolha = scanner.next().toUpperCase().charAt(0);

        if (escolha == 'S') {
            carrinho.limpar();
        }
        else {
            System.out.println("Operação cancelada");
        }


    }


}