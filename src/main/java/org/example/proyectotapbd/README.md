# Proyecto Restaurante
>Por Juan Angel Rivera Torres, Montserrat Perez Landin, Ximena Rodriguez Rodriguez alumnos del TECNM en Celaya
## Cosas por hacer
- [ ] Terminar de generar todas las tablas
- [x] Hacer que todos puedan usar el submódulo
- [ ] Verificar el diagrama de la BD con el maestro Elpidio
- [ ] Unificar todo en una misma BD
- [ ] Generar todos los modelos DAO
- [ ] Vincular tablas con DAO
- [ ] Generar interfaz para cada DAO
- [ ] Generar interfaces generales
- [ ] Vincular base de datos con el proyecto

### Actividades Juan
- [x] Categoria.java
- [x] CategoriasDAO.java
- [x] ListaCategorias.java
- [ ] Producto.java
- [ ] ProductosDAO.java
- [ ] ListaProductos.java
- [ ] Preguntar al maestro que archivos se van a generar para las clases n-n
- [x] Corregir dirección de imagenes
- [x] Creacion de clase generica para ButtonCell
 
### Actividades Monse
- [ ] Mesa.java
- [ ] MesasDAO.java
- [ ] ListaMesas.java
- [ ] Proovedor.java
- [ ] ProovedoresDAO.java
- [ ] ListaProovedores.java
- [ ] Insumo.java
- [ ] InsumosDAO.java
- [ ] ListaInsumos.java

### Actividades Ximena
- [ ] Empleado.java
- [ ] EmpleadosDAO.java
- [ ] ListaEmpleados.java
- [ ] Orden.java
- [ ] OrdenesDAO.java
- [ ] ListaOrdenes.java
- [ ] Entidades y Relaciones de la BD de manera textual
- [ ] Reservacion.java
- [ ] ReservacionesDAO.java
- [ ] ListaReservaciones.java

## Comandos básicos a usar
- **Para manejar rutas en el proyecto**


  Para volver al directorio raiz.
```bash
cd ..
```
Para ingresar al submodulo restaurante.
```bash
cd modulo
```

- **Para añadir una aportación al proyecto**
Para verificar que archivos se van a actualizar.
```bash
git status
```
Para añadir todos los archivos modificados localmente.
```bash
git add .
```
Para etiquetar el paquete a enviar.
```bash
git commit -a -m "Nombre del paquete"
```
Para actualizar repositorio local.
```bash
git pull --rebase origin main
```

Para subir los cambios a GitHub.
```bash
git push origin main
```

- **Para mysql (en la carpeta bin)** 
Para iniciar sesión como un rol.
```bash
mysql -u nombre_del_rol -p;
```
Para cerrar sesión.
```bash
quit;
```
Para seleccionar la base de datos a usar.
```bash
use Nombre_base_de_datos;
```
Muestra las tablas de la base de datos seleccionada.
```bash
show tables;
```
Muestra como está estructurada una tabla seleccionada.
```bash
decribe Nombre_tabla;
```
Muestra todos los registros de una tabla.
```bash
SELECT * FROM Nombre_tabla;
```
Para crear una nueva tabla en la base de datos.
```bash
CREATE TABLE Nombre_tabla (parametro1, parametro2, ..., parametron);
```
