package pagamento;

public class PixNacional implements Pix {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando Pix Nacional...");
    }
}
