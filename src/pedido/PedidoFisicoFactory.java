package pedido;

public class PedidoFisicoFactory extends PedidoFactory {
    @Override
    public PedidoInterface instanciarPedido() {
        return new PedidoFisico();
    }
}
