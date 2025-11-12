package pagamento;

public  interface PagamentosFactoryInterface {
    public PixInterface createPix();
   public CartaoInterface createCartao();
   public BoletoInterface createBoleto();
}
