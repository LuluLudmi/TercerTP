-- 1. Listar todos los clientes junto con sus datos de contacto.
SELECT
    c.id_cliente,
    c.nombre,
    c.apellido,
    c.email,
    c.telefono,
    c.instagram
FROM
    clientes c;

-- 2. Obtener la cantidad de stock por cada producto, ordenado de menor a mayor stock.
SELECT
    p.id_producto,
    p.nombre,
    p.stock AS cantidad_disponible
FROM
    productos p
ORDER BY
    cantidad_disponible ASC;

-- 3. Mostrar pedidos en estado 'Pendiente' junto con el nombre y apellido del cliente.
SELECT
    pe.id_pedido,
    cl.nombre AS nombre_cliente,
    cl.apellido AS apellido_cliente,
    pe.fecha_pedido,
    pe.estado_pedido
FROM
    pedidos pe
JOIN
    clientes cl ON pe.id_cliente = cl.id_cliente
WHERE
    pe.estado_pedido = 'Pendiente';

-- 4. Listar productos que tienen un precio de venta mayor a $10000.
SELECT DISTINCT
    p.nombre,
    p.precio_venta
FROM
    productos p
WHERE
    p.precio_venta > 10000;

-- 5. Obtener el total de ingresos generados por cada producto vendido, ordenado de mayor a menor ingreso.
SELECT
    p.nombre AS nombre_producto,
    SUM(dp.cantidad * p.precio_venta) AS ingresos_generados
FROM
    detalle_pedidos dp
JOIN
    productos p ON dp.id_producto = p.id_producto
GROUP BY
    p.nombre
ORDER BY
    ingresos_generados DESC;

-- 6. Mostrar el historial de pedidos de un cliente específico (ej. Sofia Colque), ordenado por fecha de pedido.
SELECT
    pe.id_pedido,
    pe.fecha_pedido,
    pe.estado_pedido,
    pe.total
FROM
    pedidos pe
JOIN
    clientes cl ON pe.id_cliente = cl.id_cliente
WHERE
    cl.nombre = 'Sofia' AND cl.apellido = 'Colque'
ORDER BY
    pe.fecha_pedido DESC;

-- 7. Listar los productos (nombre y cantidad) de un pedido particular (ej. pedido ID 1).
SELECT
    dp.id_pedido,
    pr.nombre AS nombre_producto,
    dp.cantidad
FROM
    detalle_pedidos dp
JOIN
    productos pr ON dp.id_producto = pr.id_producto
WHERE
    dp.id_pedido = 1;

-- 8. Contar el número de pedidos por cada estado.
SELECT
    estado_pedido,
    COUNT(id_pedido) AS cantidad_de_pedidos
FROM
    pedidos
GROUP BY
    estado_pedido;

-- 9. Clientes que han comprado un 'Perfume Humor Proprio'.
SELECT DISTINCT
    cl.nombre,
    cl.apellido,
    cl.instagram
FROM
    clientes cl
JOIN
    pedidos pe ON cl.id_cliente = pe.id_cliente
JOIN
    detalle_pedidos dp ON pe.id_pedido = dp.id_pedido
JOIN
    productos pr ON dp.id_producto = pr.id_producto
WHERE
    pr.nombre = 'Perfume Humor Proprio';

-- 10. Calcular el valor promedio de los pedidos que están 'Confirmado'.
SELECT
    AVG(total) AS promedio_pedidos_confirmados
FROM
    pedidos
WHERE
    estado_pedido = 'Confirmado';

-- 11. Clientes que han realizado más de un pedido y el total gastado por cada uno.
SELECT
    c.id_cliente,
    c.nombre,
    c.apellido,
    COUNT(p.id_pedido) AS total_pedidos_realizados,
    SUM(p.total) AS total_gastado
FROM
    clientes c
JOIN
    pedidos p ON c.id_cliente = p.id_cliente
GROUP BY
    c.id_cliente, c.nombre, c.apellido
HAVING
    total_pedidos_realizados > 1
ORDER BY
    total_gastado DESC;

-- 12. Productos que tienen menos de 30 unidades en stock y han sido vendidos al menos una vez.
SELECT
    pr.id_producto,
    pr.nombre,
    pr.stock,
    SUM(dp.cantidad) AS cantidad_total_vendida
FROM
    productos pr
JOIN
    detalle_pedidos dp ON pr.id_producto = dp.id_producto
WHERE
    pr.stock < 30
GROUP BY
    pr.id_producto, pr.nombre, pr.stock
HAVING
    cantidad_total_vendida > 0
ORDER BY
    pr.stock ASC;

-- 13. Pedidos que incluyen al menos 2 productos diferentes.
SELECT
    p.id_pedido,
    c.nombre AS nombre_cliente,
    c.apellido AS apellido_cliente,
    p.fecha_pedido,
    p.total,
    COUNT(dp.id_producto) AS numero_productos_diferentes
FROM
    pedidos p
JOIN
    clientes c ON p.id_cliente = c.id_cliente
JOIN
    detalle_pedidos dp ON p.id_pedido = dp.id_pedido
GROUP BY
    p.id_pedido, c.nombre, c.apellido, p.fecha_pedido, p.total
HAVING
    numero_productos_diferentes >= 2
ORDER BY
    numero_productos_diferentes DESC, p.fecha_pedido DESC;

-- 14. Ingresos totales por mes para el año 2025.
SELECT
    DATE_FORMAT(fecha_pedido, '%Y-%m') AS anio_mes,
    SUM(total) AS ingresos_mensuales
FROM
    pedidos
WHERE
    YEAR(fecha_pedido) = 2025
GROUP BY
    anio_mes
ORDER BY
    anio_mes ASC;

-- 15. Clientes que no han realizado ningún pedido.
SELECT
    c.id_cliente,
    c.nombre,
    c.apellido,
    c.email
FROM
    clientes c
LEFT JOIN
    pedidos p ON c.id_cliente = p.id_cliente
WHERE
    p.id_pedido IS NULL;

-- 16. Producto más vendido (en cantidad de unidades) y su ingreso total.
SELECT
    pr.nombre AS nombre_producto,
    SUM(dp.cantidad) AS total_unidades_vendidas,
    SUM(dp.cantidad * pr.precio_venta) AS ingreso_total_producto
FROM
    productos pr
JOIN
    detalle_pedidos dp ON pr.id_producto = dp.id_producto
GROUP BY
    pr.nombre
ORDER BY
    total_unidades_vendidas DESC
LIMIT 1;

-- 17. Listar todos los clientes y los productos que han comprado.

SELECT
    c.nombre AS nombre_cliente,
    c.apellido AS apellido_cliente,
    pr.nombre AS nombre_producto,
    dp.cantidad AS cantidad_comprada,
    p.fecha_pedido AS fecha_del_pedido
FROM
    clientes c
JOIN
    pedidos p ON c.id_cliente = p.id_cliente
JOIN
    detalle_pedidos dp ON p.id_pedido = dp.id_pedido
JOIN
    productos pr ON dp.id_producto = pr.id_producto
ORDER BY
    c.apellido, c.nombre, p.fecha_pedido DESC, pr.nombre;

-- 18. Valor promedio de los productos en stock.
SELECT
    AVG(precio_venta) AS precio_venta_promedio_stock
FROM
    productos
WHERE
    stock > 0;

-- 19. El pedido más caro y el cliente que lo realizó.
SELECT
    p.id_pedido,
    c.nombre AS nombre_cliente,
    c.apellido AS apellido_cliente,
    p.fecha_pedido,
    p.total
FROM
    pedidos p
JOIN
    clientes c ON p.id_cliente = c.id_cliente
ORDER BY
    p.total DESC
LIMIT 1;

-- 20. Historial completo de pedidos incluyendo detalles de productos para un cliente específico (ej. 'Sofia Colque').
SELECT
    c.nombre AS nombre_cliente,
    c.apellido AS apellido_cliente,
    p.id_pedido,
    p.fecha_pedido,
    p.estado_pedido,
    p.total AS total_pedido,
    pr.nombre AS nombre_producto,
    dp.cantidad AS cantidad_producto,
    (dp.cantidad * pr.precio_venta) AS subtotal_producto
FROM
    clientes c
JOIN
    pedidos p ON c.id_cliente = p.id_cliente
JOIN
    detalle_pedidos dp ON p.id_pedido = dp.id_pedido
JOIN
    productos pr ON dp.id_producto = pr.id_producto
WHERE
    c.nombre = 'Sofia' AND c.apellido = 'Colque'
ORDER BY
    p.fecha_pedido DESC, p.id_pedido ASC, pr.nombre ASC;



