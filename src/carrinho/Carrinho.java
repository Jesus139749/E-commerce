package carrinho;

import item.Item;
import item.Tipo;
import pagamento.*;
import pedido.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Carrinho {

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

        // Calcular fretes

        // Calcular descontos

        // Processar pagamentos
        pedidos.forEach(pedido -> {
            processarPagamento(pedido, new NacionalPagamentoFactory());
        });

    }

    public static void processarPagamento(PedidoInterface pedido, PagamentosFactoryInterface pagamentosFactory) {

        Scanner sc = new Scanner(System.in);

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
                boleto.pagar(pedido.getValor());
                break;
            case 2:
                PixInterface pix = pagamentosFactory.createPix();
                pix.pagar(pedido.getValor());
                break;
            case 3:
                CartaoInterface cartao = pagamentosFactory.createCartao();
                cartao.pagar(pedido.getValor());
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
