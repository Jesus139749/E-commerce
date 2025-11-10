package pedido;

import item.Item;

import java.time.LocalDateTime;
import java.util.List;

public interface Pedido {
    int gerarId();
    LocalDateTime setarData();
    void setarItens(List<Item> itens);
}
