CREATE TABLE IF NOT EXISTS cliente (
    ClienteID INT NOT NULL AUTO_INCREMENT,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(150) NOT NULL,
    fechaAlta DATE NOT NULL,
    provincia VARCHAR(100) NOT NULL,
    PRIMARY KEY (ClienteID)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Ana', 'Garcia Lopez', '2024-01-15', 'Madrid');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Luis', 'Martinez Ruiz', '2024-02-03', 'Barcelona');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Marta', 'Sanchez Perez', '2024-02-19', 'Valencia');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Carlos', 'Fernandez Diaz', '2024-03-08', 'Sevilla');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Elena', 'Lopez Martin', '2024-03-22', 'Zaragoza');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Javier', 'Gomez Romero', '2024-04-11', 'Malaga');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Lucia', 'Navarro Torres', '2024-05-06', 'Alicante');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('David', 'Jimenez Morales', '2024-05-27', 'Granada');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Sofia', 'Ruiz Alvarez', '2024-06-14', 'Murcia');

INSERT INTO cliente (nombre, apellidos, fechaAlta, provincia)
VALUES ('Pablo', 'Hernandez Castro', '2024-07-02', 'A Coruna');
