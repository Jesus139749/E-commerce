package pedido;

public class PedidoDigitalFactory extends PedidoFactory {
    @Override
    public PedidoInterface instanciarPedido() {
        return new PedidoDigital();
    }
}
