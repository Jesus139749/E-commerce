package pagamento;

public  interface PagamentosFactory {
    public  Pix createPix();
   public Cartao createCartao();
   public Boleto createBoleto();
}
