package item;

public class Item {
    public int id;
    public String nome;
    public Tipo tipo;
    public double valor;
    public int quantidade;

    public Item(int id, String nome, double valor, Tipo tipo) {
        this.id = id;
        this.nome = nome;
        this.valor = valor;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return String.format(
                "Item {\n" +
                "  id: %d,\n" +
                "  nome: '%s',\n" +
                "  tipo: %s\n" +
                "}",
                id, nome, tipo
        );
    }
}
