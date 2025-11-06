public class PedidoDigitalFactory extends PedidoFactory {
    @Override
    public Pedido instanciarPedido() {
        return new PedidoDigital();
    }
}
