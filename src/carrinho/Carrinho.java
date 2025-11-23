package carrinho;

import descontos.*;
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
    private static CalculadoraDesconto calculadoraDesconto = new CalculadoraDesconto();
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

        System.out.println("1 - Boleto (sem desconto)");
        System.out.println("2 - PIX (desconto de 10%)");
        System.out.println("3 - Cartão (desconto de 5%)");
        System.out.println("Escolha o método de pagamento: ");

        int escolhaPagamento = sc.nextInt();
        switch (escolhaPagamento) {
            case 1:
                valor -= calcularDesconto(new DescontoBoleto(), valor);
                BoletoInterface boleto = pagamentosFactory.createBoleto();
                boleto.pagar(valor);
                break;
            case 2:
                valor -= calcularDesconto(new DescontoPix(), valor);
                PixInterface pix = pagamentosFactory.createPix();
                pix.pagar(valor);
                break;
            case 3:
                valor -= calcularDesconto(new DescontoCartao(), valor);
                CartaoInterface cartao = pagamentosFactory.createCartao();
                cartao.pagar(valor);
                break;
        }

    }

    public static double calcularDesconto(DescontoInterface strategy, double valor) {
        calculadoraDesconto.setStrategy(strategy);
        double desconto = calculadoraDesconto.calcularDesconto(valor);
        System.out.printf("Desconto aplicado: R$ %.2f%n", desconto);
        System.out.printf("Valor final: R$ %.2f%n", valor - desconto);
        return desconto;
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
