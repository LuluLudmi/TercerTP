
package emprendimiento.natura.gilma.aguada.tests;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class TestConnection {
    public static void main(String[] args) {
        Properties props = new Properties();

        try (InputStream in = TestConnection.class // obtenemos el objeto class de esta clase
                .getClassLoader() // obtiene la clase que carga las clases y los recursos
                .getResourceAsStream("application.properties")) { // busca el archivo que le pasamos como parámetro
            // y lo devuelve como un flujo de bytes
            if (in == null) {
                System.err.println("No se econtró el application.properties");
                return;
            }
            props.load(in);
            // cargamos todas las propiedades en el props, es decir las combinaciones
            // clave-valor
        } catch (Exception e) {
            System.err.println("Error cargando las properties: " + e.getMessage());
        }

        // configuramos el pool de conexiones de HikariCP
        HikariConfig config = new HikariConfig();
        // configuramos la URL
        config.setJdbcUrl(props.getProperty("spring.datasource.url"));
        // configuramos el usuario
        config.setUsername(props.getProperty("spring.datasource.username"));
        // configuramos el password
        config.setPassword(props.getProperty("spring.datasource.password"));

        // creamos el DataSource con el pool de conexiones y probamos la conexión
        try (HikariDataSource ds = new HikariDataSource(config);
                Connection conn = ds.getConnection()) { // obtenemos la conexión
            if (conn.isValid(2)) {
                System.out.println("Conexión exitosa a: " + conn.getMetaData().getURL());
                // getMetaData obtiene la información de la conexión
            } else {
                System.err.println("La conexión no es válida");
            }
        } catch (Exception e) {
            System.err.println("No se pudo conectar " + e.getMessage());
        }

    }
}
