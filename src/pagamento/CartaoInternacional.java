package pagamento;

public class CartaoInternacional implements Cartao{
    @Override
    public void pagar(double valor){
        System.out.println("Pagando Cartao Internacional....");
    }
}
