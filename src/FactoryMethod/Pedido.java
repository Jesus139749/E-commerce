package FactoryMethod;

import FactoryMethod.Item.Item;

import java.time.LocalDateTime;

public interface Pedido {
    int gerarId();
    LocalDateTime setarData();
    void adicionarItem(Item item);
    void removerItem(int id);
}
