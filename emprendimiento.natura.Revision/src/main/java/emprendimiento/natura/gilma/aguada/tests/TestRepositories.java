
package emprendimiento.natura.gilma.aguada.tests;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import emprendimiento.natura.gilma.aguada.entidades.Cliente;
import emprendimiento.natura.gilma.aguada.repositories.ClienteDAO;
import emprendimiento.natura.gilma.aguada.repositories.interfaces.I_ClienteRepository;

@SpringBootApplication(scanBasePackages = "emprendimiento.natura.gilma.aguada")
public class TestRepositories {
    public static void main(String[] args) {

        try (ConfigurableApplicationContext context = SpringApplication.run(TestRepositories.class, args);) {

            I_ClienteRepository clienteDAO = context.getBean(ClienteDAO.class);

            System.out.println("--- PRUEBAS DE REPOSITORIOS (Solo Cliente) ---");

            // --- Pruebas para CLIENTE ---
            System.out.println("\n>>> Test Cliente: Creando un nuevo cliente");
            // Para 'int idCliente', pasamos 0.
            Cliente nuevoCliente = new Cliente(0, "Maria", "Gonzalez", "maria.g@example.com", "1122334455",
                    "Av. Siempre Viva 742", "@mariagonzalez_ok");
            clienteDAO.create(nuevoCliente); // Llama al método create del DAO

            if (nuevoCliente.getIdCliente() > 0) { // Usamos getIdCliente() para acceder al ID
                System.out.println(" ## Cliente creado correctamente con ID: " + nuevoCliente.getIdCliente());
                System.out.println("    Datos: " + nuevoCliente);
            } else {
                System.err.println(" ¡¡ ERROR - no se pudo crear al cliente o no se asignó un ID válido !!");
            }

            System.out.println("\n>>> Test Cliente: Buscando cliente por ID (el recién creado)");
            Cliente clienteEncontrado = clienteDAO.findById(nuevoCliente.getIdCliente());
            if (clienteEncontrado != null) {
                System.out.println(" ## Cliente encontrado: " + clienteEncontrado.getNombre() + " "
                        + clienteEncontrado.getApellido() + " (ID: " + clienteEncontrado.getIdCliente() + ")"); // Usamos
                                                                                                                // getIdCliente()
            } else {
                System.err.println(" ¡¡ ERROR - Cliente con ID " + nuevoCliente.getIdCliente() + " no encontrado !!");
            }

            System.out.println("\n>>> Test Cliente: Actualizando el cliente");
            if (clienteEncontrado != null) {
                clienteEncontrado.setTelefono("1199887766");
                int filasAfectadas = clienteDAO.update(clienteEncontrado);
                if (filasAfectadas > 0) {
                    System.out.println(" ## Cliente con ID " + clienteEncontrado.getIdCliente()
                            + " actualizado. Nuevo teléfono: " + clienteEncontrado.getTelefono());
                } else {
                    System.err.println(" ¡¡ ERROR - No se pudo actualizar el cliente !!");
                }
            }

            System.out.println("\n>>> Test Cliente: Listando todos los clientes");
            clienteDAO.findAll().forEach(c -> System.out
                    .println("    - " + c.getNombre() + " " + c.getApellido() + " (ID: " + c.getIdCliente() + ")")); // Usamos
                                                                                                                     // getIdCliente()

            System.out.println("\n--- PRUEBAS DE REPOSITORIOS FINALIZADAS ---");

        } catch (Exception e) {
            System.err.println("¡¡ ERROR GRAVE EN LA EJECUCIÓN DE LOS TESTS O LA BASE DE DATOS !!");
            e.printStackTrace();
        }
    }
}
