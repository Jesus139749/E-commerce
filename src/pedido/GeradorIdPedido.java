package pedido;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class GeradorIdPedido {

    private static final Set<Integer> idsGerados = new HashSet<>();
    private static final Random random = new Random();

    public static int gerarIdUnico() {
        int id;
        do {
            id = random.nextInt(1000);
        } while (idsGerados.contains(id));

        idsGerados.add(id);
        return id;
    }

    public static void limparIds() {
        idsGerados.clear();
    }

    public static Set<Integer> getIdsGerados() {
        return idsGerados;
    }
}
