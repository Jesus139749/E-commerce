package frete;

public class FreteNormal implements FreteInterface {
    @Override
    public double calcular(double valorPedido, double distancia) {
        return valorPedido * 0.1 + distancia * 0.5;
    }
}
