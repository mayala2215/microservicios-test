-- =========================================================
-- SCRIPT DE BASE DE DATOS - PRUEBA TÉCNICA
-- Autor: Moisés Ayala
-- =========================================================

DROP DATABASE IF EXISTS banco_clientes;
CREATE DATABASE banco_clientes CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE banco_clientes;


CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    genero VARCHAR(10),
    edad INT,
    direccion VARCHAR(150),
    telefono VARCHAR(20),
    identificacion VARCHAR(50),
    contrasena VARCHAR(100) NOT NULL,
    estado BOOLEAN DEFAULT TRUE
);


INSERT INTO clientes (nombre, genero, edad, direccion, telefono, identificacion, contrasena, estado) VALUES
('Jose Lema', 'M', 35, 'Otavalo sn y principal', '098254785', '1234567890', '1234', TRUE),
('Marianela Montalvo', 'F', 29, 'Amazonas y NN.UU.', '097548965','1234567890', '5678', TRUE),
('Juan Osorio', 'M', 42, '13 junio y Equinoccial', '098874587','1234567890', '1245', TRUE);


DROP DATABASE IF EXISTS cuentasdb;
CREATE DATABASE cuentasdb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE cuentasdb;


CREATE TABLE cuentas (
    numero_cuenta VARCHAR(50) PRIMARY KEY,
    tipo_cuenta VARCHAR(20) NOT NULL,
    saldo_inicial DECIMAL(10,2) DEFAULT 0.00,
    estado BOOLEAN DEFAULT TRUE,
    cliente_nombre VARCHAR(100) NOT NULL,
    id_cliente BIGINT
);

CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha DATETIME(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6),
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DECIMAL(10,2) NOT NULL,
    saldo DECIMAL(10,2) NOT NULL,
    saldo_disponible DECIMAL(10,2) NOT NULL,
    numero_cuenta VARCHAR(50) NOT NULL,
    FOREIGN KEY (numero_cuenta) REFERENCES cuentas(numero_cuenta),
    INDEX idx_movimientos_fecha (fecha)
);

INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_nombre, id_cliente) VALUES
('478758', 'Ahorros', 2000.00, TRUE, 'Jose Lema', 1),
('225487', 'Corriente', 100.00, TRUE, 'Marianela Montalvo', 2),
('495878', 'Ahorros', 0.00, TRUE, 'Juan Osorio', 3),
('496825', 'Ahorros', 540.00, TRUE, 'Marianela Montalvo', 2);

INSERT INTO movimientos (fecha, tipo_movimiento, valor, saldo, saldo_disponible, numero_cuenta) VALUES
('2022-02-10 10:00:00', 'Retiro', 575.00, 2000.00, 1425.00, '478758'),
('2022-02-10 11:00:00', 'Depósito', 600.00, 100.00, 700.00, '225487'),
('2022-02-10 12:00:00', 'Depósito', 150.00, 0.00, 150.00, '495878'),
('2022-02-08 09:00:00', 'Retiro', 540.00, 540.00, 0.00, '496825');

-- Clientes
SELECT * FROM banco_clientes.clientes;

-- Cuentas
SELECT * FROM cuentasdb.cuentas;

-- Movimientos
SELECT * FROM cuentasdb.movimientos;
