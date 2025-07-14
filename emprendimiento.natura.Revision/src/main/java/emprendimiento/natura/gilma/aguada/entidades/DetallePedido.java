package emprendimiento.natura.gilma.aguada.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class DetallePedido {
    private int idPedido;
    private int idProducto;
    private int cantidad;


}
