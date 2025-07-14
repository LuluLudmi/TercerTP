package emprendimiento.natura.gilma.aguada.repositories.interfaces;

import java.sql.SQLException;
import java.util.List;
import emprendimiento.natura.gilma.aguada.entidades.Producto; 

public interface I_ProductoRepository {

    /**
     * Crea un nuevo registro de producto en la base de datos.
     * @param producto El objeto Producto a guardar.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    void create(Producto producto) throws SQLException;

    /**
     * Busca un producto por su ID.
     * @param id El ID del producto a buscar.
     * @return El objeto Producto si se encuentra, o null si no existe.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    Producto findById(int idProducto) throws SQLException;

    /**
     * Obtiene una lista de todos los productos.
     * @return Una lista de objetos Producto.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Producto> findAll() throws SQLException;

    /**
     * Actualiza un registro de producto existente en la base de datos.
     * @param producto El objeto Producto con los datos actualizados.
     * @return El número de filas afectadas (normalmente 1 si la actualización fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int update(Producto producto) throws SQLException;

    /**
     * Elimina un registro de producto de la base de datos por su ID.
     * @param id El ID del producto a eliminar.
     * @return El número de filas afectadas (normalmente 1 si la eliminación fue exitosa).
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    int delete(int idProducto) throws SQLException;

    // Métodos específicos para Producto (ej. buscar por nombre o por rango de precio)
    /**
     * Busca productos por su nombre (o parte del nombre).
     * @param nombre El nombre o parte del nombre del producto a buscar.
     * @return Una lista de objetos Producto que coinciden con el nombre.
     * @throws SQLException Si ocurre un error de acceso a la base de datos.
     */
    List<Producto> findByNombre(String nombre) throws SQLException;
}
