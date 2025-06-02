-- Crear la base de datos
CREATE DATABASE restaurante;
USE restaurante;

-- Tabla Turno
CREATE TABLE Turno (
                       idTurno INT PRIMARY KEY AUTO_INCREMENT,
                       horaInicio TIME NOT NULL,
                       horaFin TIME NOT NULL,
                       descTurno VARCHAR(100)
);

-- Tabla Empleado
CREATE TABLE Empleado (
                          idEmp INT PRIMARY KEY AUTO_INCREMENT,
                          puestoEmp VARCHAR(50),
                          horario VARCHAR(50),
                          telEmp VARCHAR(15),
                          CURP VARCHAR(18) UNIQUE,
                          RFC VARCHAR(13) UNIQUE,
                          fechIngreso DATE,
                          nomEmp VARCHAR(100),
                          NSS VARCHAR(15) UNIQUE,
                          sueldoEmp DECIMAL(10,2),
                          idTurno INT,
                          FOREIGN KEY (idTurno) REFERENCES Turno(idTurno)
);

-- Tabla Usuario
CREATE TABLE Usuario (
                         idUsuario INT PRIMARY KEY AUTO_INCREMENT,
                         idEmp INT NOT NULL,
                         username VARCHAR(50) UNIQUE NOT NULL,
                         passwordHash VARCHAR(255) NOT NULL,
                         rol VARCHAR(30),
                         FOREIGN KEY (idEmp) REFERENCES Empleado(idEmp)
);

-- Tabla Proveedor
CREATE TABLE Proveedor (
                           idProv INT PRIMARY KEY AUTO_INCREMENT,
                           nomProv VARCHAR(100),
                           emailProv VARCHAR(100),
                           telProv VARCHAR(15),
                           direcProv VARCHAR(255),
                           descProv TEXT
);

-- Tabla Insumo
CREATE TABLE Insumo (
                        idIns INT PRIMARY KEY AUTO_INCREMENT,
                        descIns TEXT,
                        nomIns VARCHAR(100),
                        costoIns DECIMAL(10,2),
                        idProv INT,
                        FOREIGN KEY (idProv) REFERENCES Proveedor(idProv)
);

-- Tabla Categoría
CREATE TABLE Categoria (
                           idCat INT PRIMARY KEY AUTO_INCREMENT,
                           nomCat VARCHAR(100),
                           descCat TEXT
);

-- Tabla Producto
CREATE TABLE Producto (
                          idProd INT PRIMARY KEY AUTO_INCREMENT,
                          precio DECIMAL(10,2),
                          costo DECIMAL(10,2),
                          nomProd VARCHAR(100),
                          idCat INT,
                          FOREIGN KEY (idCat) REFERENCES Categoria(idCat)
);

-- Tabla Ingredientes (Producto - Insumo)
CREATE TABLE Ingredientes (
                              idProd INT,
                              idIns INT,
                              cantidad DECIMAL(10,2),
                              unidad VARCHAR(20),
                              PRIMARY KEY (idProd, idIns),
                              FOREIGN KEY (idProd) REFERENCES Producto(idProd),
                              FOREIGN KEY (idIns) REFERENCES Insumo(idIns)
);

-- Tabla Cliente
CREATE TABLE Cliente (
                         idCte INT PRIMARY KEY AUTO_INCREMENT,
                         telCte VARCHAR(15),
                         emailCte VARCHAR(100),
                         nomCte VARCHAR(100),
                         direcCte VARCHAR(255)
);

-- Tabla Mesa
CREATE TABLE Mesa (
                      idMesa INT PRIMARY KEY AUTO_INCREMENT,
                      capacidad INT
);

-- Tabla Reservación
CREATE TABLE Reservacion (
                             idReser INT PRIMARY KEY AUTO_INCREMENT,
                             hora TIME,
                             noInvitados INT,
                             fecha DATE,
                             idCte INT,
                             FOREIGN KEY (idCte) REFERENCES Cliente(idCte)
);

-- Tabla Mesa_Reservación (n-n)
CREATE TABLE Mesa_Reservacion (
                                  idMesa INT,
                                  idReser INT,
                                  PRIMARY KEY (idMesa, idReser),
                                  FOREIGN KEY (idMesa) REFERENCES Mesa(idMesa),
                                  FOREIGN KEY (idReser) REFERENCES Reservacion(idReser)
);

-- Tabla Orden
CREATE TABLE Orden (
                       idOrd INT PRIMARY KEY AUTO_INCREMENT,
                       total DECIMAL(10,2),
                       fecha DATETIME,
                       idEmp INT,
                       idCte INT,
                       idMesa INT,
                       FOREIGN KEY (idEmp) REFERENCES Empleado(idEmp),  -- Levanta
                       FOREIGN KEY (idCte) REFERENCES Cliente(idCte),   -- Pide
                       FOREIGN KEY (idMesa) REFERENCES Mesa(idMesa)     -- Se usa
);

-- Tabla Contiene_Producto (Orden - Producto)
CREATE TABLE Contiene_Producto (
                                   idOrd INT,
                                   idProd INT,
                                   cantidad INT,
                                   PRIMARY KEY (idOrd, idProd),
                                   FOREIGN KEY (idOrd) REFERENCES Orden(idOrd),
                                   FOREIGN KEY (idProd) REFERENCES Producto(idProd)
);

-- Tabla Pago
CREATE TABLE Pago (
                      idPago INT PRIMARY KEY AUTO_INCREMENT,
                      idOrd INT NOT NULL,
                      monto DECIMAL(10,2),
                      tipoPago VARCHAR(50),
                      fechaPago DATETIME,
                      FOREIGN KEY (idOrd) REFERENCES Orden(idOrd)
);
