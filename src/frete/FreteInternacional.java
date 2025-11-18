package frete;

public class FreteInternacional implements FreteInterface {
    @Override
    public double calcular(double valorPedido, double distancia) {
        return valorPedido * 0.3 + distancia * 1.5;
    }
}
