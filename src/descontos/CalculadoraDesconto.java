package descontos;

public class CalculadoraDesconto {

    private DescontoInterface strategy;

    public void setStrategy(DescontoInterface strategy) {
        this.strategy = strategy;
    }

    public double calcularDesconto(double valor) {
        return strategy.calcularDesconto(valor);
    }
}