package carrinho;

import item.Item;
import item.Tipo;
import pedido.*;

import java.util.ArrayList;
import java.util.List;

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

    public List<Pedido> gerarPedido() {

        List<Item> itensDigitais = itens.stream().filter(item -> item.tipo.equals(Tipo.DIGITAL)).toList();
        List<Item> itensFisicos = itens.stream().filter(item -> item.tipo.equals(Tipo.FISICO)).toList();

        List<Pedido> pedidos = new ArrayList<>();

        if (!itensDigitais.isEmpty()) {
            Pedido pedidoDigital = new PedidoDigitalFactory().instanciarPedido();
            pedidoDigital.setarItens(itensDigitais);
            pedidos.add(pedidoDigital);
        }

        if (!itensFisicos.isEmpty()) {
            Pedido pedidoFisico = new PedidoFisicoFactory().instanciarPedido();
            pedidoFisico.setarItens(itensFisicos);
            pedidos.add(pedidoFisico);
        }

        return pedidos;

    }
}
