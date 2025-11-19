package descontos;

public class PrecoFinal {
    private DescontoInterface strategyDesconto;
    private double valorInicial;

    public PrecoFinal(double valorInicial, DescontoInterface strategyDesconto) {
        this.valorInicial = valorInicial;
        this.strategyDesconto = strategyDesconto;
    }
    public double calcularPrecoFinal() {
            double desconto = strategyDesconto.calcularDesconto(valorInicial);
            return valorInicial - desconto;
    }
    public void setStrategyDesconto(DescontoInterface novaStrategy) {
            this.strategyDesconto = novaStrategy;
    }
    public void exibirDetalhes() {
        double desconto = strategyDesconto.calcularDesconto(valorInicial);
        double precoFinal = calcularPrecoFinal();

        System.out.println("=== DETALHES DO PREÇO ===");
        System.out.printf("Valor Inicial: R$ %.2f%n", valorInicial);
        System.out.printf("Desconto Aplicado: R$ %.2f%n", desconto);
        System.out.printf("Preço Final: R$ %.2f%n", precoFinal);
        System.out.println("Tipo de Strategy: " + strategyDesconto.getClass().getSimpleName());
        System.out.println("=========================\n");
    }
    public double getValorInicial() {
            return valorInicial;
    }

    public DescontoInterface getStrategyDesconto() {
        return strategyDesconto;
    }
}
