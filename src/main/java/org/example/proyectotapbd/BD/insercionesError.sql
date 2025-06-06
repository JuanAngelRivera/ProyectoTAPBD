INSERT INTO ErroresConstraint (nombreConstraint, descTurno) VALUES
-- Turno
('pk_turno', 'La clave primaria de la tabla Turno no puede estar repetida ni ser nula.'),

-- Empleado
('pk_empleado', 'La clave primaria de la tabla Empleado no puede estar repetida ni ser nula.'),
('uq_empleado_curp', 'La CURP del empleado ya está registrada.'),
('uq_empleado_rfc', 'El RFC del empleado ya está registrado.'),
('uq_empleado_nss', 'El NSS del empleado ya está registrado.'),
('fk_empleado_turno', 'El idTurno del empleado no existe en la tabla Turno.'),

-- Usuario
('pk_usuario', 'La clave primaria de la tabla Usuario no puede estar repetida ni ser nula.'),
('uq_usuario_username', 'El nombre de usuario ya está en uso.'),
('fk_usuario_empleado', 'El idEmp del usuario no existe en la tabla Empleado.'),

-- Proveedor
('pk_proveedor', 'La clave primaria de la tabla Proveedor no puede estar repetida ni ser nula.'),

-- Insumo
('pk_insumo', 'La clave primaria de la tabla Insumo no puede estar repetida ni ser nula.'),
('fk_insumo_proveedor', 'El idProv del insumo no existe en la tabla Proveedor.'),

-- Categoría
('pk_categoria', 'La clave primaria de la tabla Categoria no puede estar repetida ni ser nula.'),

-- Producto
('pk_producto', 'La clave primaria de la tabla Producto no puede estar repetida ni ser nula.'),
('fk_producto_categoria', 'El idCat del producto no existe en la tabla Categoria.'),

-- Ingredientes
('pk_ingredientes', 'No se puede duplicar la combinación de producto e insumo.'),
('fk_ingredientes_producto', 'El idProd en Ingredientes no existe en la tabla Producto.'),
('fk_ingredientes_insumo', 'El idIns en Ingredientes no existe en la tabla Insumo.'),

-- Cliente
('pk_cliente', 'La clave primaria de la tabla Cliente no puede estar repetida ni ser nula.'),

-- Mesa
('pk_mesa', 'La clave primaria de la tabla Mesa no puede estar repetida ni ser nula.'),

-- Reservación
('pk_reservacion', 'La clave primaria de la tabla Reservacion no puede estar repetida ni ser nula.'),
('fk_reservacion_cliente', 'El idCte en Reservacion no existe en la tabla Cliente.'),

-- Mesa_Reservación
('pk_mesareservacion', 'No se puede duplicar la combinación de mesa y reservación.'),
('fk_mesares_mesa', 'El idMesa en Mesa_Reservacion no existe en la tabla Mesa.'),
('fk_mesares_reservacion', 'El idReser en Mesa_Reservacion no existe en la tabla Reservacion.'),

-- Orden
('pk_orden', 'La clave primaria de la tabla Orden no puede estar repetida ni ser nula.'),
('fk_orden_empleado', 'El idEmp en Orden no existe en la tabla Empleado.'),
('fk_orden_cliente', 'El idCte en Orden no existe en la tabla Cliente.'),
('fk_orden_mesa', 'El idMesa en Orden no existe en la tabla Mesa.'),

-- Contiene_Producto
('pk_contiene', 'No se puede duplicar la combinación de orden y producto.'),
('fk_contiene_orden', 'El idOrd en Contiene_Producto no existe en la tabla Orden.'),
('fk_contiene_producto', 'El idProd en Contiene_Producto no existe en la tabla Producto.'),

-- Pago
('pk_pago', 'La clave primaria de la tabla Pago no puede estar repetida ni ser nula.'),
('fk_pago_orden', 'El idOrd en Pago no existe en la tabla Orden.');
