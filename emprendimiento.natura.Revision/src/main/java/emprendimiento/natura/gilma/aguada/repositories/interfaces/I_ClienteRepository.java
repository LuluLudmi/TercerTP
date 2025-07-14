package emprendimiento.natura.gilma.aguada.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import emprendimiento.natura.gilma.aguada.entidades.Cliente; 


public interface I_ClienteRepository {

    /**
     * Crea un nuevo registro de cliente en la base de datos.
     * @param cliente El objeto Cliente a guardar.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    void create(Cliente cliente) throws SQLException;

    /**
     * Busca un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o null si no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    Cliente findById(int idCliente) throws SQLException;

    /**
     * Obtiene una lista de todos los clientes.
     * @return Una lista de objetos Cliente.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Cliente> findAll() throws SQLException;

    /**
     * Actualiza un registro de cliente existente en la base de datos.
     * @param cliente El objeto Cliente con los datos actualizados.
     * @return El número de filas afectadas (normalmente 1 si la actualización fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int update(Cliente cliente) throws SQLException;

    /**
     * Elimina un registro de cliente de la base de datos por su ID.
     * @param id El ID del cliente a eliminar.
     * @return El número de filas afectadas (normalmente 1 si la eliminación fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int delete(int idCliente) throws SQLException;

    /**
     * Busca clientes por una parte de su apellido.
     * @param apellido La cadena a buscar en el apellido del cliente.
     * @return Una lista de objetos Cliente que coinciden con el apellido.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Cliente> findByApellido(String apellido) throws SQLException;

    // Métodos específicos para Cliente (ej. buscar por email, ya que es un campo que tienes)
    /**
     * Busca un cliente por su dirección de email.
     * @param email La dirección de email del cliente a buscar.
     * @return El objeto Cliente si se encuentra, o null si no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    Cliente findByEmail(String email) throws SQLException;
}