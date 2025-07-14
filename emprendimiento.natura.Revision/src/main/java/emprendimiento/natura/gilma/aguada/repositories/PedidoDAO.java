
package emprendimiento.natura.gilma.aguada.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import emprendimiento.natura.gilma.aguada.entidades.Pedido;
import emprendimiento.natura.gilma.aguada.enums.EstadoPedido;
import emprendimiento.natura.gilma.aguada.repositories.interfaces.I_PedidoRepository;

@Repository
public class PedidoDAO implements I_PedidoRepository {

    private final DataSource dataSource;

    // Consultas SQL para la tabla 'pedidos'
    private static final String SQL_CREATE =
        "INSERT INTO pedidos (id_cliente, fecha_pedido, estado_pedido, total) VALUES (?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID =
        "SELECT id_pedido, id_cliente, fecha_pedido, estado_pedido, total FROM pedidos WHERE id_pedido = ?";
    private static final String SQL_FIND_ALL =
        "SELECT id_pedido, id_cliente, fecha_pedido, estado_pedido, total FROM pedidos";
    private static final String SQL_UPDATE =
        "UPDATE pedidos SET id_cliente = ?, fecha_pedido = ?, estado_pedido = ?, total = ? WHERE id_pedido = ?";
    private static final String SQL_DELETE =
        "DELETE FROM pedidos WHERE id_pedido = ?";
    private static final String SQL_FIND_BY_CLIENTE =
        "SELECT id_pedido, id_cliente, fecha_pedido, estado_pedido, total FROM pedidos WHERE id_cliente = ?";
    private static final String SQL_FIND_BY_ESTADO =
        "SELECT id_pedido, id_cliente, fecha_pedido, estado_pedido, total FROM pedidos WHERE estado_pedido = ?";

    public PedidoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Pedido pedido) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, pedido.getIdCliente());
            ps.setObject(2, pedido.getFechaPedido()); 

           
            ps.setString(3, pedido.getEstadoPedido().getDbValue());
            ps.setDouble(4, pedido.getTotal());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                   
                    pedido.setIdPedido(keys.getInt(1)); // Asigna el ID autogenerado
                }
            }
        }
    }

    @Override
    public Pedido findById(int idPedido) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, idPedido);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_ALL);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                lista.add(mapRow(rs));
            }
        }
        return lista;
    }

    @Override
    public int update(Pedido pedido) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, pedido.getIdCliente());
            ps.setObject(2, pedido.getFechaPedido());

            ps.setString(3, pedido.getEstadoPedido().getDbValue());
            ps.setDouble(4, pedido.getTotal());
           
            ps.setInt(5, pedido.getIdPedido()); 

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public int delete(int idPedido) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idPedido);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public List<Pedido> findByCliente(int idCliente) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_CLIENTE)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public List<Pedido> findByEstado(EstadoPedido estado) throws SQLException {
        List<Pedido> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ESTADO)) {
            ps.setString(1, estado.getDbValue()); // Busca por el String de la DB
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    // Método auxiliar para mapear una fila de ResultSet a un objeto Pedido
    private Pedido mapRow(ResultSet rs) throws SQLException {
        LocalDateTime fechaPedido = rs.getObject("fechaPedido", LocalDateTime.class);

        String estadoDbValue = rs.getString("estadoPedido");
        EstadoPedido estadoPedido = null;
        if (estadoDbValue != null) {
            estadoPedido = EstadoPedido.fromDbValue(estadoDbValue);
        }

        return new Pedido(
            rs.getInt("idPedido"), 
            rs.getInt("idCliente"), 
            fechaPedido,
            estadoPedido,
            rs.getDouble("total") 
        );
    }
}