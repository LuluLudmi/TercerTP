package emprendimiento.natura.gilma.aguada.repositories.interfaces;
import java.sql.SQLException;
import java.util.List;
import emprendimiento.natura.gilma.aguada.entidades.DetallePedido; 


public interface I_DetallePedidoRepository {

    /**
     * Crea un nuevo registro de detalle de pedido en la base de datos.
     * @param detallePedido El objeto DetallePedido a guardar.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    void create(DetallePedido detallePedido) throws SQLException;

    /**
     * Busca un detalle de pedido por su ID de pedido y ID de producto (clave compuesta).
     * @param idPedido El ID del pedido al que pertenece el detalle.
     * @param idProducto El ID del producto en el detalle.
     * @return El objeto DetallePedido si se encuentra, o null si no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    DetallePedido findById(int idPedido, int idProducto) throws SQLException;

    /**
     * Obtiene una lista de todos los detalles de pedidos.
     * @return Una lista de objetos DetallePedido.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<DetallePedido> findAll() throws SQLException;

    /**
     * Actualiza un registro de detalle de pedido existente en la base de datos.
     * @param detallePedido El objeto DetallePedido con los datos actualizados.
     * @return El número de filas afectadas (normalmente 1 si la actualización fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int update(DetallePedido detallePedido) throws SQLException;

    /**
     * Elimina un registro de detalle de pedido de la base de datos por su ID de pedido y ID de producto (clave compuesta).
     * @param idPedido El ID del pedido al que pertenece el detalle a eliminar.
     * @param idProducto El ID del producto en el detalle a eliminar.
     * @return El número de filas afectadas (normalmente 1 si la eliminación fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int delete(int idPedido, int idProducto) throws SQLException;

    // Métodos específicos para DetallePedido (ej. buscar por ID de pedido)
    /**
     * Busca todos los detalles de un pedido específico.
     * @param idPedido El ID del pedido.
     * @return Una lista de objetos DetallePedido que pertenecen a ese pedido.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<DetallePedido> findByPedido(int idPedido) throws SQLException;
}
