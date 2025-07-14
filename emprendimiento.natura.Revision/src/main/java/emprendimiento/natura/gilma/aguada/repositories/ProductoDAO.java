
package emprendimiento.natura.gilma.aguada.repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.stereotype.Repository;

import emprendimiento.natura.gilma.aguada.entidades.Producto;
import emprendimiento.natura.gilma.aguada.repositories.interfaces.I_ProductoRepository; 

@Repository
public class ProductoDAO implements I_ProductoRepository {

    private final DataSource dataSource;

    // Consultas SQL para la tabla 'productos'
    private static final String SQL_CREATE =
        "INSERT INTO productos (nombre, descripcion, precio_costo, precio_venta, stock) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_ID =
        "SELECT id_producto, nombre, descripcion, precio_costo, precio_venta, stock FROM productos WHERE id_producto = ?";
    private static final String SQL_FIND_ALL =
        "SELECT id_producto, nombre, descripcion, precio_costo, precio_venta, stock FROM productos";
    private static final String SQL_UPDATE =
        "UPDATE productos SET nombre = ?, descripcion = ?, precio_costo = ?, precio_venta = ?, stock = ? WHERE id_producto = ?";
    private static final String SQL_DELETE =
        "DELETE FROM productos WHERE id_producto = ?";
    private static final String SQL_FIND_BY_NOMBRE =
        "SELECT id_producto, nombre, descripcion, precio_costo, precio_venta, stock FROM productos WHERE nombre LIKE ?";

    public ProductoDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void create(Producto producto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
           
            ps.setDouble(3, producto.getPrecioCosto());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getStock());

            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                   
                    producto.setIdProducto(keys.getInt(1)); // Asigna el ID autogenerado
                }
            }
        }
    }

    @Override
    public Producto findById(int idProducto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Producto> findAll() throws SQLException {
        List<Producto> lista = new ArrayList<>();
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
    public int update(Producto producto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_UPDATE)) {

            ps.setString(1, producto.getNombre());
            ps.setString(2, producto.getDescripcion());
            ps.setDouble(3, producto.getPrecioCosto());
            ps.setDouble(4, producto.getPrecioVenta());
            ps.setInt(5, producto.getStock());
            ps.setInt(6, producto.getIdProducto());

            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public int delete(int idProducto) throws SQLException {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idProducto);
            int filasAfectadas = ps.executeUpdate();
            return filasAfectadas;
        }
    }

    @Override
    public List<Producto> findByNombre(String nombre) throws SQLException {
        List<Producto> lista = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(SQL_FIND_BY_NOMBRE)) {
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapRow(rs));
                }
            }
        }
        return lista;
    }

    // Método auxiliar para mapear una fila de ResultSet a un objeto Producto
    private Producto mapRow(ResultSet rs) throws SQLException {
        return new Producto(
         
            rs.getInt("idProducto"),
            rs.getString("nombre"),
            rs.getString("descripcion"),
            rs.getDouble("precioCosto"), 
            rs.getDouble("precioVenta"), 
            rs.getInt("stock")
        );
    }
}