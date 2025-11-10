import carrinho.Carrinho;
import item.Item;
import item.Tipo;
import pedido.Pedido;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Carrinho carrinho = new Carrinho();

        carrinho.adicionarItem(new Item(1, "Curso do Pablo Marçal", 1, 2000, Tipo.DIGITAL));
        carrinho.adicionarItem(new Item(2, "Curso de Dropshipping do Thiago Finch", 1, 2000, Tipo.DIGITAL));
        carrinho.adicionarItem(new Item(3, "monitor", 1, 500, Tipo.FISICO));

        List<Pedido> pedidos = carrinho.gerarPedido();

        System.out.println(pedidos);

    }
}