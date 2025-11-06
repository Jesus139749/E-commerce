package FactoryMethod;

public class PedidoFisicoFactory extends PedidoFactory {
    @Override
    public Pedido instanciarPedido() {
        return new PedidoFisico();
    }
}
