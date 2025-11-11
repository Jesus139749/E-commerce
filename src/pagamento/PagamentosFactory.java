package pagamento;

public abstract interface PagamentosFactory {
    public abstract Pix createPix();
    public abstract Cartao createCartao();
    public abstract Boleto createBoleto();
}
