package FactoryMethod;

import FactoryMethod.Item.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PedidoDigital implements Pedido {

    public int id;
    public double valor;
    public LocalDateTime data;
    public List<Item> itens = new ArrayList<>();

    public int gerarId() {
        Random random =new Random();
        return random.nextInt(1000);
    }

    public LocalDateTime setarData(){
        return LocalDateTime.now();
    }

    public void adicionarItem(Item item) {
        this.itens.add(item);
    }

    public void removerItem(int id) {
        this.itens.removeIf(item -> item.id == id);
    }

}
