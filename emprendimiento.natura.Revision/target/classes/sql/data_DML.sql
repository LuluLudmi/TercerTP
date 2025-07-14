
-- Inserta registros de clientes en la tabla 'clientes'.
INSERT INTO clientes (nombre, apellido, email, telefono, direccion, instagram) VALUES
('Sofia', 'Colque', 'sofia.soyyo@hotmail.com', '1165432100', 'Av. Las Flores 100, Palermo', '@sofia_soyyolover'),
('Paula', 'Gomez', 'paulagomez345@gmail.com', '1150009988', 'Calle del Sol 25, Caballito', '@paula_skincare'),
('Laura', 'Mamani', 'laura.mami21@gmail.com', '1133221144', 'Rivadavia 876, Almagro', '@laura_tumami_arg'),
('Micaela', 'Fernandez', 'micaelaSeSe@hotmail.com', '1177665544', 'calle 2 3124, Berazategui', '@mica_sese_fan'),
('Romina', 'Perez', 'romi.perez88@gmail.com', '1122334455', 'Av. Mitre 500, Avellaneda', '@romi_belleza_ok'),
('Diego', 'Gimenez', 'diegogimez_ok@hotmail.com', '1133445566', 'Calle 12 1234, Quilmes', '@diego.create_quilmes'),
('Valeria', 'Rodriguez', 'vale.rodri@yahoo.com.ar', '1144556677', 'Libertad 890, Lanús', '@valeria_lanus_beauty'),
('Lucas', 'Torres', 'lucas_torres_arg@gmail.com', '1155667788', 'San Martín 200, Florencio Varela', '@lucas.torres.argen'),
('Florencia', 'Diaz', 'flor.diaz.natural@hotmail.com', '1166778899', 'España 300, Lomas de Zamora', '@florencia_diazlife'),
('Gonzalo', 'Acosta', 'gonza.acosta7@gmail.com', '1177889900', 'Moreno 1500, Solano', '@gonza.acosta.oksi'),
('Carla', 'Lopez', 'carli_lopez_siempre@hotmail.com', '1188990011', 'Calle 25 de Mayo 700, Bernal', '@carla.lolopez.bernal'),
('Martin', 'Sanchez', 'tincho.sanchez.ok@gmail.com', '1199001122', 'Roca 450, Almirante Brown', '@martin_san_oficial'),
('Sofia', 'Martinez', 'sofimartinez_arg@yahoo.com.ar', '1111223344', 'Av. Calchaquí 1800, Cruce Varela', '@sofi.m.kiss'),
('Ezequiel', 'Ruiz', 'eze.ruiz.conurba@gmail.com', '1122334400', 'Garibaldi 100, Quilmes Oeste', '@eze.ruiz.online'),
('Andrea', 'Blanco', 'andy_blanco_7@hotmail.com', '1133445500', 'Larrea 600, Banfield', '@andy_white.banfield'),
('Pablo', 'Peralta', 'pablo.peralta.ok@gmail.com', '1144556600', 'Mitre 1200, Monte Grande', '@pablo_peralta_ventas'),
('Marina', 'Castro', 'mari.castro.belleza@yahoo.com.ar', '1155667700', 'Las Heras 200, Adrogué', '@marina.castro.life'),
('Federico', 'Nuñez', 'fede.nunez.arg@gmail.com', '1166778800', 'Eva Perón 900, Burzaco', '@fede.gree.burzaco'),
('Julieta', 'Herrera', 'juli.herrera.conur@hotmail.com', '1177889900', 'Calle Maipú 400, Berazategui', '@julieta.ventas.natura'),
('Ricardo', 'Medina', 'ricardo.medina.ventas@gmail.com', '1188990000', 'Independencia 1100, Glew', '@ricardo_dina_online');

-- Inserta registros de productos en la tabla 'productos' con precios actualizados para Argentina.
INSERT INTO productos (nombre, descripcion, precio_costo, precio_venta, stock) VALUES
('Hidratante Corporal Frambuesa y Pimienta Rosa', 'Crema hidratante 400ml, fragancia intensa', 8500.00, 13900.00, 50),
('Perfume Humor Proprio', 'Eau de Toilette femenino 75ml, floral y amaderado', 25000.00, 42000.00, 30),
('Base Fluida HD', 'Base de alta cobertura FPS 15, tono medio', 12000.00, 20500.00, 40),
('Labial Cremoso Nude', 'Labial cremoso hidratante, color nude clásico', 4500.00, 7800.00, 60),
('Shampoo Murumuru Reconstruccion', 'Shampoo reparador para cabello dañado 300ml', 7000.00, 11800.00, 45),
('Jabones Cremosos Surtidos x5', 'Caja con 5 jabones cremosos, varias fragancias', 5000.00, 8500.00, 70),
('Desodorante Roll-on Invisible', 'Desodorante antitranspirante 72hs, sin manchas', 3000.00, 5200.00, 80),
('Aceite Trifásico Pitanga', 'Aceite corporal post-baño 200ml, fragancia cítrica', 10000.00, 16800.00, 35),
('Máscara de Pestañas Una', 'Máscara para volumen y alargamiento', 7500.00, 12900.00, 25),
('Crema de Manos Castaña', 'Crema hidratante para manos 75g, nutrición intensa', 4000.00, 6900.00, 90);

-- Inserta registros de pedidos en la tabla 'pedidos'.

INSERT INTO pedidos (id_cliente, fecha_pedido, estado_pedido, total) VALUES
(1, '2025-06-09 14:00:00', 'Confirmado', 0.00), 
(2, '2025-06-10 16:30:00', 'Pendiente', 0.00),  
(1, '2025-06-05 10:15:00', 'Enviado', 0.00),   
(3, '2025-06-15 11:00:00', 'Pendiente', 0.00),   
(5, '2025-06-18 09:30:00', 'Confirmado', 0.00),  
(6, '2025-06-19 15:45:00', 'Enviado', 0.00),  
(8, '2025-06-20 12:00:00', 'Pendiente', 0.00),   
(10, '2025-06-21 10:00:00', 'Confirmado', 0.00), 
(4, '2025-06-12 17:00:00', 'Entregado', 0.00),  
(15, '2025-06-20 18:00:00', 'Pendiente', 0.00);  

-- Inserta registros de detalle de pedidos en la tabla 'detalle_pedidos'.

INSERT INTO detalle_pedidos (id_pedido, id_producto, cantidad) VALUES
(1, 1, 2), 
(1, 4, 1), 
(2, 2, 1), 
(2, 6, 1), 
(3, 3, 1), 
(3, 1, 1),  
(4, 5, 2), 
(5, 7, 3), 
(5, 1, 1), 
(6, 8, 1), 
(7, 9, 1), 
(7, 4, 1), 
(8, 10, 2), 
(9, 2, 1), 
(9, 6, 1),
(10, 5, 1);


