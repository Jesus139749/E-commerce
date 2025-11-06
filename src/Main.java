import FactoryMethod.Item.Item;
import FactoryMethod.Item.Tipo;
import FactoryMethod.Pedido;
import FactoryMethod.PedidoDigitalFactory;
import FactoryMethod.PedidoFactory;
import FactoryMethod.PedidoFisicoFactory;

public class Main {
    public static void main(String[] args) {
        PedidoFactory factoryDigital = new PedidoDigitalFactory();
        PedidoFactory factoryFisico = new PedidoFisicoFactory();

        Pedido pedidoDigital = factoryDigital.instanciarPedido();
        pedidoDigital.adicionarItem(new Item(1, "notebook", 1, Tipo.FISICO));
        pedidoDigital.adicionarItem(new Item(2, "monitor", 1, Tipo.FISICO));
        System.out.println(pedidoDigital);
    }
}