CREATE DATABASE IF NOT EXISTS clinicadb CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE clinicadb;
CREATE TABLE IF NOT EXISTS paciente (    
    id_paciente INT AUTO_INCREMENT PRIMARY KEY,    
    nombre VARCHAR(100) NOT NULL,    
    edad INT,    
    telefono VARCHAR(20),    
    correo VARCHAR(100) UNIQUE);
CREATE TABLE IF NOT EXISTS cita (    
    id_cita INT AUTO_INCREMENT PRIMARY KEY,    
    fecha DATE NOT NULL,    
    num_consultorio INT NOT NULL,    
    id_paciente INT NOT NULL,    
    FOREIGN KEY (id_paciente) REFERENCES paciente(id_paciente) ON DELETE CASCADE,    INDEX idx_paciente (id_paciente));
    
INSERT INTO paciente (nombre, edad, telefono, correo) VALUES ('Juan Pérez', 30, '123456789', 'juan@email.com');
INSERT INTO cita (fecha, num_consultorio, id_paciente) VALUES ('2026-02-10', 1, 1);

select * from paciente

