package pagamento;

public class PixNacional implements PixInterface {
    @Override
    public void pagar(double valor){

        System.out.println("=== Pagamento Nacional por PIX ===");
        System.out.printf("Valor total: R$ %.2f%n", valor);

        System.out.println("Processando pagamento...");

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Erro ao processar pagamento: " + e);
        }

        System.out.println("Pagamento via PIX realizado com sucesso!");
    }
}
