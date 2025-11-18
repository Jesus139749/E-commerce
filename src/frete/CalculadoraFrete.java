package frete;

public class CalculadoraFrete {

    private FreteInterface strategy;

    public void setStrategy(FreteInterface strategy) {
        this.strategy = strategy;
    }

    public double calcular(double valorPedido, double distancia) {
        return strategy.calcular(valorPedido, distancia);
    }

}
