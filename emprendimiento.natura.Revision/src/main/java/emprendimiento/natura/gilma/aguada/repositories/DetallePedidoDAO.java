
package emprendimiento.natura.gilma.aguada.repositories;

import emprendimiento.natura.gilma.aguada.entidades.DetallePedido;
import emprendimiento.natura.gilma.aguada.repositories.interfaces.I_DetallePedidoRepository;

import javax.sql.DataSource; // Para la gestión de conexiones (pooling)
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetallePedidoDAO implements I_DetallePedidoRepository {

    private final DataSource dataSource;

    // Definición de las consultas SQL, usando nombres de columnas en snake_case
    // ¡IMPORTANTE!: Estos nombres deben coincidir EXACTAMENTE con los de tu tabla 'detalle_pedidos' en la DB
    private static final String SQL_INSERT = "INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad) VALUES (?, ?, ?)";
    private static final String SQL_SELECT_BY_ID = "SELECT id_pedido, id_producto, cantidad FROM detalle_pedidos WHERE id_pedido = ? AND id_producto = ?";
    private static final String SQL_SELECT_ALL = "SELECT id_pedido, id_producto, cantidad FROM detalle_pedidos";
    private static final String SQL_UPDATE = "UPDATE detalle_pedidos SET cantidad = ? WHERE id_pedido = ? AND id_producto = ?";
    private static final String SQL_DELETE = "DELETE FROM detalle_pedidos WHERE id_pedido = ? AND id_producto = ?";
    private static final String SQL_SELECT_BY_PEDIDO = "SELECT id_pedido, id_producto, cantidad FROM detalle_pedidos WHERE id_pedido = ?";


    // Constructor para inyectar el DataSource
    public DetallePedidoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    // Método auxiliar para mapear un ResultSet a un objeto DetallePedido
    // Aquí es donde se "traducen" los nombres de columna de la DB (snake_case)
    // a las propiedades del objeto Java (CamelCase)
    private DetallePedido mapRow(ResultSet rs) throws SQLException {
        return new DetallePedido(
            rs.getInt("id_pedido"),      // Columna DB: id_pedido -> Propiedad Java: idPedido
            rs.getInt("id_producto"),    // Columna DB: id_producto -> Propiedad Java: idProducto
            rs.getInt("cantidad")
        );
    }

    @Override
    public void create(DetallePedido detallePedido) throws SQLException {
        // try-with-resources para asegurar que Connection y PreparedStatement se cierren automáticamente
        try (Connection conn = dataSource.getConnection();
             // No se necesita Statement.RETURN_GENERATED_KEYS ya que las PKs no son auto-generadas aquí
             PreparedStatement ps = conn.prepareStatement(SQL_INSERT)) {

            // Establece los parámetros de la consulta usando los getters del objeto DetallePedido
            ps.setInt(1, detallePedido.getIdPedido());
            ps.setInt(2, detallePedido.getIdProducto());
            ps.setInt(3, detallePedido.getCantidad());

            ps.executeUpdate(); // Ejecuta la inserción
        }
    }

    @Override
    public DetallePedido findById(int idPedido, int idProducto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_ID)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs); // Si encuentra un resultado, lo mapea
                }
            }
        }
        return null; // Si no encuentra, retorna null
    }

    @Override
    public List<DetallePedido> findAll() throws SQLException {
        List<DetallePedido> detalles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_ALL);
             ResultSet rs = ps.executeQuery()) { // Ejecuta la consulta
            while (rs.next()) {
                detalles.add(mapRow(rs)); // Itera y agrega todos los detalles encontrados
            }
        }
        return detalles;
    }

    @Override
    public int update(DetallePedido detallePedido) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {
            // Solo se actualiza la cantidad para una clave compuesta dada
            ps.setInt(1, detallePedido.getCantidad());
            // Las IDs de la clave compuesta se usan en la cláusula WHERE
            ps.setInt(2, detallePedido.getIdPedido());
            ps.setInt(3, detallePedido.getIdProducto());
            return ps.executeUpdate(); // Ejecuta la actualización
        }
    }

    @Override
    public int delete(int idPedido, int idProducto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idPedido);
            ps.setInt(2, idProducto);
            return ps.executeUpdate(); // Ejecuta la eliminación
        }
    }

    @Override
    public List<DetallePedido> findByPedido(int idPedido) throws SQLException {
        List<DetallePedido> detalles = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_SELECT_BY_PEDIDO)) {
            ps.setInt(1, idPedido); // Parámetro para el ID del pedido
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    detalles.add(mapRow(rs));
                }
            }
        }
        return detalles;
    }
}
