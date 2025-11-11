package pagamento;

public class PixInternacional implements Pix{
    @Override
    public void  pagar(double valor){
        System.out.println("Pagando Pix Internacional....");
    }

}
