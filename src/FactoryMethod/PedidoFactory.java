package FactoryMethod;

public abstract class PedidoFactory {

    public abstract Pedido instanciarPedido();

    public void teste() {
        instanciarPedido().teste();
    }

}
