package pagamento;

public class PixInternacional implements PixInterface {
    @Override
    public void  pagar(double valor){
        System.out.println("Pagando PixInterface Internacional....");
    }

}
