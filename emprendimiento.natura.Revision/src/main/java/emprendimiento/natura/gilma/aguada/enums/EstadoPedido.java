
package emprendimiento.natura.gilma.aguada.enums;

public enum EstadoPedido {
    PENDIENTE("Pendiente"),
    CONFIRMADO("Confirmado"),
    ENVIADO("Enviado"),
    ENTREGADO("Entregado"),
    CANCELADO("Cancelado");

    private final String dbValue;

    EstadoPedido(String dbValue) {
        this.dbValue = dbValue;
    }

    public String getDbValue() {
        return dbValue;
    }

    public static EstadoPedido fromDbValue(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Estado de pedido nulo.");
        }
        String trimmedValue = value.trim();

        System.out.println("DEBUG: Valor de DB recibido: '" + value + "' (length: " + value.length() + ")");
        System.out.println("DEBUG: Valor TRIMEADO: '" + trimmedValue + "' (length: " + trimmedValue.length() + ")");
       
        for (EstadoPedido estado : EstadoPedido.values()) {
            if (estado.dbValue.equalsIgnoreCase(trimmedValue)) {
                return estado;
            }
        }
        throw new IllegalArgumentException("Estado de pedido desconocido: '" + value + "'");
    }
}