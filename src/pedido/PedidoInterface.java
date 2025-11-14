package pedido;

import item.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public interface PedidoInterface {
    int gerarId();
    LocalDateTime setarData();
    void setarItens(List<Item> itens);
    int getId();
    double getValor();
    String getDataFormatada();
    List<Item> getItens();
}
