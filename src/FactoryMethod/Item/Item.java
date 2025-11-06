package FactoryMethod.Item;

public class Item {
    public int id;
    public String nome;
    public int quantidade;
    public Tipo tipo;

    public Item(int id, String nome, int quantidade, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format(
                "Item {\n" +
                "  id: %d,\n" +
                "  nome: '%s',\n" +
                "  quantidade: %d,\n" +
                "  tipo: %s\n" +
                "}",
                id, nome, quantidade, tipo
        );
    }
}
