package FactoryMethod;

import FactoryMethod.Item.Item;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PedidoFisico implements Pedido {

    private int id;
    private double valor;
    private LocalDateTime data;
    private List<Item> itens = new ArrayList<>();

    public PedidoFisico() {
        this.id = this.gerarId();
        this.data = this.setarData();
        this.valor = 0;
    }

    public int gerarId() {
        Random random = new Random();
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

    // Getters

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public LocalDateTime getData() {
        return data;
    }

    public List<Item> getItens() {
        return itens;
    }
}
