import item.Item;
import item.Tipo;
import pedido.Pedido;
import pedido.PedidoDigitalFactory;
import pedido.PedidoFactory;
import pedido.PedidoFisicoFactory;

public class Main {
    public static void main(String[] args) {

        PedidoFactory factoryDigital = new PedidoDigitalFactory();
        PedidoFactory factoryFisico = new PedidoFisicoFactory();

        Pedido pedidoDigital = factoryDigital.instanciarPedido();
        pedidoDigital.adicionarItem(new Item(1, "notebook", 1, 2000, Tipo.DIGITAL));
        pedidoDigital.adicionarItem(new Item(2, "monitor", 1, 500, Tipo.DIGITAL));
        pedidoDigital.removerItem(2);

        System.out.println(pedidoDigital);
    }
}