package pagamento;

public class BoletoNacional implements BoletoInterface {
    @Override
   public void pagar(double valor){
        System.out.println("Pagando BoletoInterface Nacional...");
    }
}
