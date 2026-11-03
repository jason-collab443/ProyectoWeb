-- =========================
--   PERFILES
-- =========================
INSERT INTO perfiles (alias, telefono, direccion, fecha_nacimiento) VALUES
 ('andresr', '3104567890', 'Cra 12 #34-56', '1990-05-12'),
 ('linaq', '3159876543', 'Cl 45 #67-89', '1988-10-21'),
 ('juanm', '3006543210', 'Av 3 #45-23', '1995-07-08'),
 ('carolinap', '3017654321', 'Cl 80 #10-45', '1992-03-14');

-- =========================
--   ROLES
-- =========================
INSERT INTO roles (nombre, descripcion, nivel_acceso, activo) VALUES
 ('ADMIN', 'Acceso total al sistema', 10, 1),
 ('SUPERVISOR', 'Supervisa operaciones y reportes', 7, 1),
 ('USUARIO', 'Acceso a funciones básicas', 3, 1),
 ('INVITADO', 'Acceso restringido', 1, 0);

-- =========================
--   PERMISOS
-- =========================
INSERT INTO permisos (recurso, accion, permitido, notas) VALUES
 ('productos', 'crear', 1, 'Puede agregar nuevos productos'),
 ('productos', 'eliminar', 0, 'Restringido a administradores'),
 ('clientes', 'listar', 1, 'Acceso general'),
 ('facturas', 'modificar', 1, 'Solo supervisores y admin');

-- =========================
--   SESIONES
-- =========================
INSERT INTO sesiones (usuario, token, ip, inicio) VALUES
 ('andresr', 'tok_123abc', '192.168.0.12', '2025-10-27 08:00'),
 ('linaq', 'tok_456def', '192.168.0.15', '2025-10-27 09:10'),
 ('juanm', 'tok_789ghi', '192.168.0.21', '2025-10-27 10:25'),
 ('carolinap', 'tok_987xyz', '192.168.0.33', '2025-10-27 11:00');

-- =========================
--   PRODUCTOS
-- =========================
INSERT INTO productos (nombre, descripcion, precio, stock) VALUES
 ('Teclado mecánico', 'Switches brown retroiluminado', 250000.00, 20),
 ('Mouse inalámbrico', 'Sensor óptico 1600dpi', 85000.00, 50),
 ('Monitor 24 pulgadas', 'Full HD IPS 75Hz', 620000.00, 15),
 ('Base para portátil', 'Ajustable de aluminio', 120000.00, 40);

-- =========================
--   INVENTARIO_MOVIMIENTOS
-- =========================
INSERT INTO inventario_movimientos (producto_codigo, tipo, cantidad, fecha) VALUES
 ('PROD-001', 'ENTRADA', 20, '2025-10-25'),
 ('PROD-001', 'SALIDA', 5, '2025-10-26'),
 ('PROD-002', 'ENTRADA', 50, '2025-10-24'),
 ('PROD-003', 'SALIDA', 2, '2025-10-26');

-- =========================
--   PROVEEDORES
-- =========================
INSERT INTO proveedores (nombre, contacto, telefono, ciudad) VALUES
 ('Tech Import SAS', 'Carlos Pérez', '6012345678', 'Bogotá'),
 ('Distribuciones Norte', 'Ana López', '6048765432', 'Medellín'),
 ('Electronix Andina', 'Pedro Torres', '6029988776', 'Cali'),
 ('CompuWorld', 'Laura Suárez', '6053344556', 'Barranquilla');

-- =========================
--   ORDEN_COMPRA
-- =========================
INSERT INTO orden_compra (numero, proveedor_nombre, fecha, total) VALUES
 ('OC-2025-001', 'Tech Import SAS', '2025-10-20', 4800000.00),
 ('OC-2025-002', 'Distribuciones Norte', '2025-10-21', 1250000.00),
 ('OC-2025-003', 'Electronix Andina', '2025-10-22', 750000.00),
 ('OC-2025-004', 'CompuWorld', '2025-10-23', 2300000.00);

-- =========================
--   CLIENTES
-- =========================
INSERT INTO clientes (nombre, correo, telefono, fecha_registro) VALUES
 ('Juan Martínez', 'juan.martinez@mail.com', '3001234567', '2025-08-15'),
 ('Lucía Gómez', 'lucia.gomez@mail.com', '3156547890', '2025-09-02'),
 ('Carlos Rivera', 'carlos.rivera@mail.com', '3123334444', '2025-09-25'),
 ('María Torres', 'maria.torres@mail.com', '3105556666', '2025-10-01');

-- =========================
--   FACTURAS
-- =========================
INSERT INTO facturas (numero, cliente_nombre, fecha, total) VALUES
 ('FAC-001', 'Juan Martínez', '2025-10-20', 620000.00),
 ('FAC-002', 'Lucía Gómez', '2025-10-21', 250000.00),
 ('FAC-003', 'Carlos Rivera', '2025-10-22', 850000.00),
 ('FAC-004', 'María Torres', '2025-10-23', 1250000.00);

-- =========================
--   DETALLE_FACTURA
-- =========================
INSERT INTO detalle_factura (factura_numero, producto_nombre, cantidad, precio_unitario) VALUES
 ('FAC-001', 'Monitor 24 pulgadas', 1, 620000.00),
 ('FAC-002', 'Teclado mecánico', 1, 250000.00),
 ('FAC-003', 'Mouse inalámbrico', 10, 85000.00),
 ('FAC-004', 'Base para portátil', 5, 120000.00);

-- =========================
--   PAGOS
-- =========================
INSERT INTO pagos (referencia, factura_numero, metodo, fecha) VALUES
 ('REF-001', 'FAC-001', 'Tarjeta crédito', '2025-10-21'),
 ('REF-002', 'FAC-002', 'Transferencia', '2025-10-22'),
 ('REF-003', 'FAC-003', 'Efectivo', '2025-10-23'),
 ('REF-004', 'FAC-004', 'PSE', '2025-10-24');
