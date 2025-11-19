package descontos;

public class DescontoPix implements DescontoInterface{
    @Override

    public double calcularDesconto(double valor){
        return valor*0.10;
    }
}
