CREATE DATABASE prueba;
use prueba;

-- Creación de la tabla Persona
CREATE TABLE tbl_persona (
    id INT PRIMARY KEY IDENTITY(1,1),
    nombre VARCHAR(50),
    genero VARCHAR(10),
    edad INT,
    identificacion VARCHAR(20),
    direccion VARCHAR(100) ,
    telefono VARCHAR(20)
);

-- Creación de la tabla Cliente
CREATE TABLE tbl_cliente (
    id INT PRIMARY KEY,
    contrasena VARCHAR(50),
    estado VARCHAR(10),
    CONSTRAINT fk_cliente_id FOREIGN KEY (id) REFERENCES tbl_persona(id)
);

-- Creación de la tabla Cuenta
CREATE TABLE tbl_cuenta (
    numero_cuenta INT PRIMARY KEY,
    tipo VARCHAR(50),
    saldo_inicial FLOAT,
    estado VARCHAR(10),
	cliente_id INT,
);

-- Creación de la tabla Movimientos
CREATE TABLE tbl_movimientos (
    movimiento_id INT PRIMARY KEY IDENTITY(1,1),
	numero_cuenta INT,
    fecha DATE,
    tipo_movimiento VARCHAR(50),
    valor FLOAT,
    saldo FLOAT,
	FOREIGN KEY (numero_cuenta) REFERENCES tbl_cuenta(numero_cuenta)
);

INSERT INTO tbl_persona (nombre, genero, edad, identificacion, direccion, telefono) VALUES
('Juan Perez', 'Masculino', 30, '123456789', 'Calle 123', '555-1234'),
('Maria Rodriguez', 'Femenino', 25, '987654321', 'Avenida 456', '555-5678'),
('Pedro Hernandez', 'Masculino', 40, '456123789', 'Carrera 789', '555-9012'),
('Luisa Gomez', 'Femenino', 35, '789123456', 'Calle 456', '555-3456'),
('Andres Ramirez', 'Masculino', 27, '321654987', 'Avenida 789', '555-7890');

INSERT INTO tbl_cliente (id,contrasena, estado) VALUES
(1,'123456', 'true'),
(2,'987654', 'true'),
(3,'456123', 'false'),
(4,'789123', 'true'),
(5,'321654', 'false');

INSERT INTO tbl_cuenta (numero_cuenta, tipo, saldo_inicial, estado,cliente_id) VALUES
(123451, 'ahorros', 1000.0, 'true',1),
(234562, 'corriente', 5000.0, 'true',2),
(345673, 'ahorros', 2000.0, 'false',3),
(456784, 'corriente', 3000.0, 'true',4),
(567895, 'ahorros', 4000.0, 'false',5);

INSERT INTO tbl_movimientos (numero_cuenta, fecha, tipo_movimiento, valor, saldo) VALUES
(123451, '2022-05-03', 'abono', 500.0, 1500.0),
(234562, '2022-05-04', 'retiro', 1000.0, 4000.0),
(345673, '2022-05-04', 'abono', 1500.0, 3500.0),
(456784, '2022-05-05', 'retiro', 2000.0, 1000.0),
(567895, '2022-05-05', 'abono', 3000.0, 7000.0);

SELECT * FROM tbl_persona;
SELECT * FROM tbl_cliente;
SELECT * FROM tbl_cuenta;
SELECT * FROM tbl_movimientos;