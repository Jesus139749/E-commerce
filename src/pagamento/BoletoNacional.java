package pagamento;

public class BoletoNacional implements Boleto{
    @Override
   public void pagar(double valor){
        System.out.println("Pagando Boleto Nacional...");
    }
}
