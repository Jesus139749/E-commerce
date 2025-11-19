package descontos;

public class DescontoCartao implements DescontoInterface{
    @Override

    public double calcularDesconto( double valor){
        return valor*0.05;
    }
}
