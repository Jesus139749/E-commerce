package pedido;

import item.Item;

import java.time.LocalDateTime;
import java.util.List;

public interface PedidoInterface {
    int gerarId();
    LocalDateTime setarData();
    void setarItens(List<Item> itens);
}
