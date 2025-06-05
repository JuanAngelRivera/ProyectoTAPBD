-- Crear la base de datos
CREATE DATABASE restaurante;
USE restaurante;

-- Tabla Turno
CREATE TABLE Turno (
                       idTurno INT AUTO_INCREMENT,
                       horaInicio TIME NOT NULL,
                       horaFin TIME NOT NULL,
                       descTurno VARCHAR(100),
                       CONSTRAINT pk_turno PRIMARY KEY (idTurno)
);

-- Tabla Empleado
CREATE TABLE Empleado (
                          idEmp INT AUTO_INCREMENT,
                          puestoEmp VARCHAR(50),
                          horario VARCHAR(50),
                          telEmp VARCHAR(15),
                          CURP VARCHAR(18),
                          RFC VARCHAR(13),
                          fechIngreso DATE,
                          nomEmp VARCHAR(100),
                          NSS VARCHAR(15),
                          sueldoEmp DECIMAL(10,2),
                          idTurno INT,
                          CONSTRAINT pk_empleado PRIMARY KEY (idEmp),
                          CONSTRAINT uq_empleado_curp UNIQUE (CURP),
                          CONSTRAINT uq_empleado_rfc UNIQUE (RFC),
                          CONSTRAINT uq_empleado_nss UNIQUE (NSS),
                          CONSTRAINT fk_empleado_turno FOREIGN KEY (idTurno) REFERENCES Turno(idTurno)
);

-- Tabla Proveedor
CREATE TABLE Proveedor (
                           idProv INT AUTO_INCREMENT,
                           nomProv VARCHAR(100),
                           emailProv VARCHAR(100),
                           telProv VARCHAR(15),
                           direcProv VARCHAR(255),
                           descProv TEXT,
                           CONSTRAINT pk_proveedor PRIMARY KEY (idProv)
);

-- Tabla Insumo
CREATE TABLE Insumo (
                        idIns INT AUTO_INCREMENT,
                        descIns TEXT,
                        nomIns VARCHAR(100),
                        costoIns DECIMAL(10,2),
                        idProv INT,
                        CONSTRAINT pk_insumo PRIMARY KEY (idIns),
                        CONSTRAINT fk_insumo_proveedor FOREIGN KEY (idProv) REFERENCES Proveedor(idProv)
);

-- Tabla Categoría
CREATE TABLE Categoria (
                           idCat INT AUTO_INCREMENT,
                           nomCat VARCHAR(100),
                           descCat TEXT,
                           CONSTRAINT pk_categoria PRIMARY KEY (idCat)
);

-- Tabla Producto
CREATE TABLE Producto (
                          idProd INT AUTO_INCREMENT,
                          precio DECIMAL(10,2),
                          costo DECIMAL(10,2),
                          nomProd VARCHAR(100),
                          idCat INT,
                          imagen VARCHAR(255),
                          CONSTRAINT pk_producto PRIMARY KEY (idProd),
                          CONSTRAINT fk_producto_categoria FOREIGN KEY (idCat) REFERENCES Categoria(idCat)

);

-- Tabla Cliente
CREATE TABLE Cliente (
                         idCte INT AUTO_INCREMENT,
                         telCte VARCHAR(15),
                         emailCte VARCHAR(100),
                         nomCte VARCHAR(100),
                         direccion VARCHAR(255),
                         CONSTRAINT pk_cliente PRIMARY KEY (idCte)
                         CONSTRAINT uq_email UNIQUE (emailCte),
);

-- Tabla Mesa
CREATE TABLE Mesa (
                      idMesa INT AUTO_INCREMENT,
                      capacidad INT,
                      ocupada BOOLEAN DEFAULT FALSE,
                      CONSTRAINT pk_mesa PRIMARY KEY (idMesa)
);

-- Tabla Reservación
CREATE TABLE Reservacion (
                             idReser INT AUTO_INCREMENT,
                             hora TIME,
                             noInvitados INT,
                             fecha DATE,
                             idCte INT,
                             CONSTRAINT pk_reservacion PRIMARY KEY (idReser),
                             CONSTRAINT fk_reservacion_cliente FOREIGN KEY (idCte) REFERENCES Cliente(idCte)
);

-- Tabla Orden
CREATE TABLE Orden (
                       idOrd INT AUTO_INCREMENT,
                       total DECIMAL(10,2),
                       fecha DATETIME,
                       idEmp INT,
                       idCte INT,
                       idMesa INT,
                       CONSTRAINT pk_orden PRIMARY KEY (idOrd),
                       CONSTRAINT fk_orden_empleado FOREIGN KEY (idEmp) REFERENCES Empleado(idEmp),
                       CONSTRAINT fk_orden_cliente FOREIGN KEY (idCte) REFERENCES Cliente(idCte),
                       CONSTRAINT fk_orden_mesa FOREIGN KEY (idMesa) REFERENCES Mesa(idMesa)
);

-- Tabla Pago
CREATE TABLE Pago (
                      idPago INT AUTO_INCREMENT,
                      idOrd INT,
                      monto DECIMAL(10,2),
                      idTipoPago INT,
                      fechaPago DATETIME,
                      CONSTRAINT pk_pago PRIMARY KEY (idPago),
                      CONSTRAINT fk_pago_orden FOREIGN KEY (idOrd) REFERENCES Orden(idOrd),
                      CONSTRAINT fk_tipo_pago FOREIGN KEY (idTipoPago) REFERENCES TipoPago(idTipoPago)
);

-- Tabla ErroresConstraint
CREATE TABLE ErroresConstraint (
                                   nombreConstraint VARCHAR(100) PRIMARY KEY,
                                   descripcion TEXT
);

-- Tabla TipoPago
CREATE TABLE TipoPago(
                    idTipoPago INT AUTO_INCREMENT,
                    descripcion TEXT,
                    CONSTRAINT pk_tipo_pago PRIMARY KEY (idTipoPago)
);

-- Tabla Ingredientes
CREATE TABLE Ingredientes(
                   idIns int,
                   idProd int,
                   cantidad int,
                   CONSTRAINT pk_ingredientes PRIMARY KEY (idIns, idProd),
                   CONSTRAINT foreign key (idIns) references Insumo(idIns),
                   CONSTRAINT foreign key (idProd) references Producto(idProd)
);