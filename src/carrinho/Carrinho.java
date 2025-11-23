package carrinho;

import frete.CalculadoraFrete;
import frete.FreteInternacional;
import frete.FreteNormal;
import item.Item;
import item.Tipo;
import pagamento.*;
import pedido.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrinho {

    private static Scanner sc = new Scanner(System.in);

    private static CalculadoraFrete calculadoraFrete = new CalculadoraFrete();
    private static PagamentosFactoryInterface pagamentosFactory;

    private List<Item> itens = new ArrayList<>();
    private double valor;

    public void adicionarItem(Item item) {
        this.itens.add(item);
        this.atualizarValor();
    }

    public void removerItem(int id) {
        this.itens.removeIf(item -> item.id == id);
        this.atualizarValor();
    }

    public void atualizarValor() {
        double valorAtualizado = 0;
        for (Item item : itens) {
            valorAtualizado += item.valor;
        }
        this.valor = valorAtualizado;
    }

    public List<PedidoInterface> gerarPedidos() {

        List<Item> itensDigitais = itens.stream().filter(item -> item.tipo.equals(Tipo.DIGITAL)).toList();
        List<Item> itensFisicos = itens.stream().filter(item -> item.tipo.equals(Tipo.FISICO)).toList();

        List<PedidoInterface> pedidos = new ArrayList<>();

        if (!itensDigitais.isEmpty()) {
            PedidoInterface pedidoDigital = new PedidoDigitalFactory().instanciarPedido();
            pedidoDigital.setarItens(itensDigitais);
            pedidos.add(pedidoDigital);
        }

        if (!itensFisicos.isEmpty()) {
            PedidoInterface pedidoFisico = new PedidoFisicoFactory().instanciarPedido();
            pedidoFisico.setarItens(itensFisicos);
            pedidos.add(pedidoFisico);
        }

        return pedidos;

    }

    public static void finalizarPedidos(List<PedidoInterface> pedidos) {

        System.out.println("Escolha o país de entrega: ");
        System.out.println("1 - Brasil");
        System.out.println("2 - Outro");

        int escolhaPais = sc.nextInt();
        double distancia = 0;

        if (escolhaPais == 1) {
            calculadoraFrete.setStrategy(new FreteNormal());
            distancia = 50;
            pagamentosFactory = new NacionalPagamentoFactory();
        }
        else if (escolhaPais == 2) {
            calculadoraFrete.setStrategy(new FreteInternacional());
            distancia = 150;
            pagamentosFactory = new InternacionalPagamentoFactory();
        }

        for (PedidoInterface pedido : pedidos) {

            double valorPedido = pedido.getValor();

            // Cálculo de fretes
            double frete = calcularFrete(pedido, distancia);
            valorPedido += frete;

            // Cálculo de descontos
            System.out.println("Escolha a opção de Desconto: ");
            System.out.println("1- Pix(Desconto de 10%) ");
            System.out.println("2- Cartão(Desconto de 5%) ");
            System.out.println("3- Boleto(Sem Desconto) ");

            int opcaoDesconto = sc.nextInt();

            descontos.DescontoInterface descontoStrategy;

            switch (opcaoDesconto) {
                case 1:
                    descontoStrategy = new descontos.DescontoPix();
                    System.out.println("Pagamento com Pix (Desconto de 10%)");
                    break;
                case 2:
                    descontoStrategy = new descontos.DescontoCartao();
                    System.out.println("Pagamento com Cartão (Desconto de 5%) ");
                    break;
                case 3:
                    descontoStrategy = new descontos.DescontoBoleto();
                    System.out.println("Pagamento com Boleto (Sem Desconto)");
                    break;

                default:
                    descontoStrategy = new descontos.DescontoBoleto();
                    System.out.println("  Opção inválida, sem desconto");

            }
            double desconto = descontoStrategy.calcularDesconto(valorPedido);
            valorPedido -= desconto;

            System.out.printf("Desconto aplicado: R$ %.2f%n", desconto);
            System.out.printf("Valor final: R$ %.2f%n", valorPedido);

            // Processar pagamentos
            processarPagamento(valorPedido);
        }

    }

    public static double calcularFrete(PedidoInterface pedido, double distancia) {

        if (pedido instanceof PedidoDigital) {
            return 0;
        }

        return calculadoraFrete.calcular(pedido.getValor(), distancia);
    }

    public static void processarPagamento(double valor) {

        System.out.println("=".repeat(50));
        System.out.println("CHECKOUT");
        System.out.println("=".repeat(50));

        System.out.println("1 - Boleto");
        System.out.println("2 - PIX");
        System.out.println("3 - Cartão");
        System.out.println("Escolha o método de pagamento: ");

        int escolhaPagamento = sc.nextInt();
        switch (escolhaPagamento) {
            case 1:
                BoletoInterface boleto = pagamentosFactory.createBoleto();
                boleto.pagar(valor);
                break;
            case 2:
                PixInterface pix = pagamentosFactory.createPix();
                pix.pagar(valor);
                break;
            case 3:
                CartaoInterface cartao = pagamentosFactory.createCartao();
                cartao.pagar(valor);
                break;
        }

    }

    public List<Item> getItens() {
        return itens;
    }

    public double getValor() {
        return valor;
    }

    public void limpar() {
        this.itens.clear();
    }
}
