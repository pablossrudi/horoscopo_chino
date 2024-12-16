# Horóscopo Chino
##Este proyecto se creo con Java EE, utilizando servlet, jsp y jdbc
### Estructura de Carpetas
```
├───.idea
├───.mvn
│   └───wrapper
├───src
│   ├───main
│   │   ├───java
│   │   │   └───com
│   │   │       └───edutecno
│   │   │           └───horoscopo_chino
│   │   │               ├───configuration
│   │   │               ├───dto
│   │   │               ├───mapper
│   │   │               ├───model
│   │   │               ├───repository
│   │   │               ├───service
│   │   │               └───servlet
│   │   ├───resources
│   │   │   ├───META-INF
│   │   │   ├───.env
│   │   │   └───horoscopodb.sql
│   │   └───webapp
│   │       ├───WEB-INF
│   │       ├───bienvenida.jsp
│   │       ├───cambiarContrasena.jsp
│   │       ├───editarUsuario.jsp
│   │       ├───index.jsp
│   │       ├───listarUsuarios.jsp
│   │       ├───profile.jsp
│   │       └───register.jsp
│   └───test
│       ├───java
│       └───resources
```
### MySQL
Se utilizo una base de datos mysql, así que se debe utilizar las mismas tablas que se encuentra en horoscopodb.sql, en la carpeta resources.
Ademas, en horoscopodb.sql también hay inserts de prueba.
### .env
En la carpeta resources debes crear un archivo .env en el cual se encuentran las variables de la base de datos,
```
DRIVER_DB=driver de la base de datos usada (en este caso el de MySQL: com.mysql.cj.jdbc.Driver)
URL_DB=url de conexión a la base de datos (ejemplo: jdbc:mysql://localhost:3306/horoscopodb)
USER_DB=usuario de base de datos
PASS_DB=contraseña de base de datos
```
