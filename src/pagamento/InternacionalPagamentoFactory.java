package pagamento;

public class InternacionalPagamentoFactory implements PagamentosFactory{

    @Override
    public Pix createPix() {
        return new PixInternacional();
    }

    @Override
    public Cartao createCartao() {
        return new CartaoInternacional();
    }

    @Override
    public Boleto createBoleto() {
        return new BoletoInternacional();
    }
}
