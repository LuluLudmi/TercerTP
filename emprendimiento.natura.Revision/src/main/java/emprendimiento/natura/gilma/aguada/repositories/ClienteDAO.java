
package emprendimiento.natura.gilma.aguada.repositories; // Ajustado a tu estructura

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import emprendimiento.natura.gilma.aguada.entidades.Cliente; 
import emprendimiento.natura.gilma.aguada.repositories.interfaces.I_ClienteRepository; 

@Repository
public class ClienteDAO implements I_ClienteRepository {

    private final DataSource dataSource;

    // Consultas SQL para la tabla 'clientes'
    private static final String SQL_CREATE =
        "INSERT INTO clientes (nombre, apellido, email, telefono, direccion, instagram) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID =
        "SELECT id_cliente, nombre, apellido, email, telefono, direccion, instagram FROM clientes WHERE id_cliente = ?";
    private static final String SQL_FIND_ALL =
        "SELECT id_cliente, nombre, apellido, email, telefono, direccion, instagram FROM clientes";
    private static final String SQL_UPDATE =
        "UPDATE clientes SET nombre = ?, apellido = ?, email = ?, telefono = ?, direccion = ?, instagram = ? WHERE id_cliente = ?";
    private static final String SQL_DELETE =
        "DELETE FROM clientes WHERE id_cliente = ?";
    private static final String SQL_FIND_BY_APELLIDO =
        "SELECT id_cliente, nombre, apellido, email, telefono, direccion, instagram FROM clientes WHERE apellido LIKE ?";
    private static final String SQL_FIND_BY_EMAIL =
        "SELECT id_cliente, nombre, apellido, email, telefono, direccion, instagram FROM clientes WHERE email = ?";

    public ClienteDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Cliente cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getInstagram());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    cliente.setIdCliente(keys.getInt(1)); // Asigna el ID autogenerado
                }
            }
        }
    }

    @Override
    public Cliente findById(int idCliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, idCliente);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Cliente> findAll() throws SQLException {
        List<Cliente> lista = new ArrayList<>();
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
    public int update(Cliente cliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getTelefono());
            ps.setString(5, cliente.getDireccion());
            ps.setString(6, cliente.getInstagram());
            ps.setInt(7, cliente.getIdCliente()); 


            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public int delete(int idCliente) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idCliente);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public List<Cliente> findByApellido(String apellido) throws SQLException {
        List<Cliente> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_APELLIDO)) {
            ps.setString(1, "%" + apellido + "%"); // Búsqueda por parte del apellido
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    @Override
    public Cliente findByEmail(String email) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_EMAIL)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    // Método auxiliar para mapear una fila de ResultSet a un objeto Cliente
   
    private Cliente mapRow(ResultSet rs) throws SQLException {
        return new Cliente(
            rs.getInt("id_cliente"), 
            rs.getString("nombre"),
            rs.getString("apellido"),
            rs.getString("email"),
            rs.getString("telefono"),
            rs.getString("direccion"),
            rs.getString("instagram")
        );
    }
}