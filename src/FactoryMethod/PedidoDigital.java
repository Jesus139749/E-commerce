package FactoryMethod;

import java.time.LocalDateTime;
import java.util.Random;

public class PedidoDigital implements Pedido {

    public int id;
    public double valor;
    public LocalDateTime data;

    public int gerarId() {
        Random random =new Random();
        return random.nextInt(1000);
    }

    public LocalDateTime setarData(){
        return LocalDateTime.now();
    }

    public void teste() {
        System.out.println("teste");
    }
}
