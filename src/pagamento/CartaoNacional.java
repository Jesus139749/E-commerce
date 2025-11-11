package pagamento;

public class CartaoNacional implements Cartao{
    @Override
    public void pagar(double valor){
        System.out.println("Pagando Cartao Nacional...");
    }
}
