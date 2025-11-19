package pagamento;

public class BoletoInternacional implements BoletoInterface {

    private final double TAXA_IOF = 0.035;

    @Override
    public void pagar(double valor){

        double iof = valor * TAXA_IOF;
        double total = valor + iof;

        System.out.println("Gerando boleto internacional");
        System.out.printf("Valor base: R$ %.2f%n", valor);
        System.out.printf("IOF (3.5%%): R$ %.2f%n", iof);
        System.out.printf("Valor final: R$ %.2f %n", total);

        System.out.println("Processando pagamento...");

        try {
            Thread.sleep(3000);
        }
        catch (InterruptedException e) {
            System.out.println("Erro ao processar pagamento: " + e);
        }

        System.out.println("Pagamento do boleto realizado com sucesso!");
    }
}
