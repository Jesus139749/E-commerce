package pagamento;

public class InternacionalPagamentoFactory implements PagamentosFactoryInterface {

    @Override
    public PixInterface createPix() {
        return new PixInternacional();
    }

    @Override
    public CartaoInterface createCartao() {
        return new CartaoInternacional();
    }

    @Override
    public BoletoInterface createBoleto() {
        return new BoletoInternacional();
    }
}
