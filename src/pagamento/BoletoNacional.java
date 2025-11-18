package pagamento;

public class BoletoNacional implements BoletoInterface {
    @Override
   public void pagar(double valor){
        if (valor <= 0) {
            System.out.println("Valor do boleto inválido!");
            return;
        }

        System.out.println("Gerando boleto nacional");
        System.out.printf("Valor: R$ %.2f %n", valor);

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
