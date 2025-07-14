
DROP TABLE IF EXISTS detalle_pedidos;
DROP TABLE IF EXISTS pedidos;
DROP TABLE IF EXISTS productos;
DROP TABLE IF EXISTS clientes;


CREATE TABLE clientes (
    id_cliente INT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(65) NOT NULL,            
    apellido VARCHAR(65) NOT NULL,           
    email VARCHAR(75) NOT NULL,              
    telefono VARCHAR(20) NOT NULL,            
    direccion VARCHAR(120) NOT NULL,         
    instagram VARCHAR(50)                    
);

CREATE TABLE productos (
    id_producto INT AUTO_INCREMENT PRIMARY KEY, 
    nombre VARCHAR(65) NOT NULL,               
    descripcion VARCHAR(80) NOT NULL,          
    precio_costo DOUBLE NOT NULL,              
    precio_venta DOUBLE NOT NULL,             
    stock INT NOT NULL                         
);

CREATE TABLE pedidos (
    id_pedido INT AUTO_INCREMENT PRIMARY KEY,                                    
    id_cliente INT NOT NULL,                                                      
    fecha_pedido DATETIME NOT NULL,                                              
    estado_pedido ENUM('Pendiente', 'Confirmado', 'Enviado', 'Entregado', 'Cancelado') NOT NULL DEFAULT 'Pendiente', 
    total DOUBLE NOT NULL,                                                       
    FOREIGN KEY (id_cliente) REFERENCES clientes(id_cliente)                      
);

CREATE TABLE detalle_pedidos (
    id_pedido INT,                                      
    id_producto INT,                                       
    cantidad INT NOT NULL,                                           
    PRIMARY KEY (id_pedido, id_producto),                   
    FOREIGN KEY (id_pedido) REFERENCES pedidos(id_pedido),   
    FOREIGN KEY (id_producto) REFERENCES productos(id_producto) 
);



