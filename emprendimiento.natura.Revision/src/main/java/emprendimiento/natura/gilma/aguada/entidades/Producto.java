package emprendimiento.natura.gilma.aguada.entidades;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion;
    private double precioCosto;
    private double precioVenta;
    private int stock;

}
