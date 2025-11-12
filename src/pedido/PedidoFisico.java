package pedido;

import item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PedidoFisico implements PedidoInterface {

    private int id;
    private double valor;
    private LocalDateTime data;
    private List<Item> itens = new ArrayList<>();

    public PedidoFisico() {
        this.id = this.gerarId();
        this.data = this.setarData();
    }

    public int gerarId() {
        Random random = new Random();
        return random.nextInt(1000);
    }

    public LocalDateTime setarData() {
        return LocalDateTime.now();
    }

    public void setarItens(List<Item> itens) {
        this.itens = itens;
        atualizarValor();
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
                "PedidoFisico {\n" +
                "  id_Pedido: %d,\n" +
                "  valor: R$ %.2f,\n" +
                "  data: %s,\n" +
                "  itens: %s\n" +
                "}",
                id, valor, data.format(formatter), itens
        );
    }
}
