package pagamento;

public class BoletoInternacional implements Boleto {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando Boleto Internacional....");
    }
}
