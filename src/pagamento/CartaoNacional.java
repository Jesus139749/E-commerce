package pagamento;

public class CartaoNacional implements CartaoInterface {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando CartaoInterface Nacional...");
    }
}
