package pagamento;

public class CartaoNacional implements CartaoInterface {
    @Override
    public void pagar(double valor){
        System.out.println("=== Pagamento Internacional no Cartão ===");
        System.out.printf("Valor total cobrado: R$ %.2f%n", valor);

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Erro ao processar pagamento: " + e);
        }

        System.out.println("Pagamento nacional via cartão realizado com sucesso!");
    }
}
