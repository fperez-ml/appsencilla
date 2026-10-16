# appsencilla

Esta aplicación es una adaptación del código de los videos siguientes:
- https://youtu.be/ZV0EgwqcEoc?si=4_ulA2ZsYelN7Y5W
- https://youtu.be/8vd6rgmeCEY?si=6nUOZPok-0YuIiR8

## Configuración de MariaDB

La conexión se configura en `src/main/resources/configuracion.properties`.
Actualiza el host, puerto, base de datos, usuario y contraseña según tu
instalación. El script `src/main/resources/db/cliente.sql` crea la tabla
`cliente` si no existe y añade diez clientes sintéticos; ejecútalo en la base
de datos configurada antes de iniciar la aplicación.

Ambos archivos están en los recursos de Maven, por lo que se incluyen en el
artefacto del proyecto. Ejecuta `mvn test` para comprobar el esquema, los datos
de ejemplo y la inclusión de la configuración.
