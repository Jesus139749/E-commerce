import FactoryMethod.PedidoDigitalFactory;
import FactoryMethod.PedidoFactory;
import FactoryMethod.PedidoFisicoFactory;

public class Main {
    public static void main(String[] args) {
        PedidoFactory pedidoDigital = new PedidoDigitalFactory();
        PedidoFactory pedidoFisico = new PedidoFisicoFactory();
        pedidoFisico.teste();
    }
}