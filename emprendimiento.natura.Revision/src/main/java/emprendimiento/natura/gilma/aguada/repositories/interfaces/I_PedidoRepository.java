package emprendimiento.natura.gilma.aguada.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import emprendimiento.natura.gilma.aguada.entidades.Pedido; 


public interface I_PedidoRepository {

    /**
     * Crea un nuevo registro de pedido en la base de datos.
     * @param pedido El objeto Pedido a guardar.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    void create(Pedido pedido) throws SQLException;

    /**
     * Busca un pedido por su ID.
     * @param id El ID del pedido a buscar.
     * @return El objeto Pedido si se encuentra, o null si no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    Pedido findById(int idPedido) throws SQLException;

    /**
     * Obtiene una lista de todos los pedidos.
     * @return Una lista de objetos Pedido.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Pedido> findAll() throws SQLException;

    /**
     * Actualiza un registro de pedido existente en la base de datos.
     * @param pedido El objeto Pedido con los datos actualizados.
     * @return El número de filas afectadas (normalmente 1 si la actualización fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int update(Pedido pedido) throws SQLException;

    /**
     * Elimina un registro de pedido de la base de datos por su ID.
     * @param id El ID del pedido a eliminar.
     * @return El número de filas afectadas (normalmente 1 si la eliminación fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int delete(int idPedido) throws SQLException;

    // Métodos específicos para Pedido (ej. buscar por cliente, por estado, por fecha)
    /**
     * Busca pedidos asociados a un cliente específico.
     * @param idCliente El ID del cliente.
     * @return Una lista de objetos Pedido realizados por el cliente.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Pedido> findByCliente(int idCliente) throws SQLException;

    /**
     * Busca pedidos por su estado.
     * @param estado El estado del pedido (usando tu enum EstadoPedido).
     * @return Una lista de objetos Pedido con el estado especificado.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Pedido> findByEstado(emprendimiento.natura.gilma.aguada.enums.EstadoPedido estado) throws SQLException;
}
