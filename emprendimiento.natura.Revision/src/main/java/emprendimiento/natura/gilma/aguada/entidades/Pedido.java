package emprendimiento.natura.gilma.aguada.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import emprendimiento.natura.gilma.aguada.enums.EstadoPedido;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pedido {
    private int idPedido;
    private int idCliente;
    private LocalDateTime fechaPedido;
    private EstadoPedido estadoPedido;
    private double total;
}
