package pagamento;

public class NacionalPagamentoFactory implements PagamentosFactory{

    @Override
    public Pix createPix() {
        return new PixNacional();
    }

    @Override
    public Cartao createCartao() {
        return new CartaoNacional();
    }

    @Override
    public Boleto createBoleto() {
        return new BoletoNacional();
    }
}
