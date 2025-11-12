package pagamento;

public class BoletoInternacional implements BoletoInterface {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando BoletoInterface Internacional....");
    }
}
