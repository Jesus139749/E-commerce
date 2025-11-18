package frete;

public class FreteExpresso implements FreteInterface {
    @Override
    public double calcular(double valorPedido, double distancia) {
        return valorPedido * 0.2 + distancia * 0.8;
    }
}
