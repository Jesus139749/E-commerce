package pagamento;

public class PixNacional implements PixInterface {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando PixInterface Nacional...");
    }
}
