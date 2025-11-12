package pagamento;

public class CartaoInternacional implements CartaoInterface {
    @Override
    public void pagar(double valor){
        System.out.println("Pagando CartaoInterface Internacional....");
    }
}
