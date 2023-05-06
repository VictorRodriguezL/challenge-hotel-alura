CREATE DATABASE control_de_reservas;
USE control_de_reservas;

CREATE TABLE usuarios(
id INT AUTO_INCREMENT,
usuario VARCHAR(30) NOT NULL,
contrasenia VARCHAR(10) NOT NULL,
PRIMARY KEY (id))Engine=InnoDB;

CREATE TABLE huesped(
id INT AUTO_INCREMENT,
nombre VARCHAR(50) NOT NULL,
apellido VARCHAR(100) NOT NULL,
fecha_nacimiento DATE NOT NULL,
nacionalidad VARCHAR(50) NOT NULL,
telefono VARCHAR(30) NOT NULL,
reserva_id INT,
PRIMARY KEY (id))Engine=InnoDB;

CREATE TABLE reserva(
id INT AUTO_INCREMENT,
fecha_entrada DATE NOT NULL,
fecha_salida DATE NOT NULL,
valor DECIMAL(10,2) NOT NULL,
forma_pago VARCHAR(35) NOT NULL,
PRIMARY KEY (id))Engine=InnoDB;

ALTER TABLE huesped ADD FOREIGN KEY (reserva_id) 
REFERENCES reserva(id)
ON UPDATE CASCADE
ON DELETE CASCADE;