package pagamento;

public class NacionalPagamentoFactory implements PagamentosFactoryInterface {

    @Override
    public PixInterface createPix() {
        return new PixNacional();
    }

    @Override
    public CartaoInterface createCartao() {
        return new CartaoNacional();
    }

    @Override
    public BoletoInterface createBoleto() {
        return new BoletoNacional();
    }
}
