package pedido;

import item.Item;
import item.Tipo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PedidoDigital implements Pedido {

    private int id;
    private double valor;
    private LocalDateTime data;
    private List<Item> itens = new ArrayList<>();

    public PedidoDigital() {
        this.id = this.gerarId();
        this.data = this.setarData();
        this.valor = 0;
    }

    public int gerarId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public LocalDateTime setarData() {
        return LocalDateTime.now();
    }

    public void adicionarItem(Item item) {

        if (!item.tipo.equals(Tipo.DIGITAL)) {
            throw new IllegalArgumentException("O tipo do pedido deve ser digital");
        }

        this.itens.add(item);
        this.atualizarValor();

    }

    public void removerItem(int id) {
        this.itens.removeIf(item -> item.id == id);
        this.atualizarValor();
    }

    public void atualizarValor() {
        double valorAtualizado = 0;
        for (Item item : itens) {
            valorAtualizado += item.valor;
        }
        this.valor = valorAtualizado;
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

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format(
                "PedidoDigital {\n" +
                "  id: %d,\n" +
                "  valor: R$ %.2f,\n" +
                "  data: %s,\n" +
                "  itens: %s\n" +
                "}",
                id, valor, data.format(formatter), itens
        );
    }
}
