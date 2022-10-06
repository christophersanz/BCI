# API user - BCI

## Iniciando

Siga las siguientes instrucciones para probar el desarrollo de este proyecto.

### Pre-Requisitos

#### Configuracion de Java:

* Descargar la versión 11 del JDk segun el sistema operativo, para Linux ([lista de JDK](https://www.oracle.com/java/technologies/downloads/#java11-windows))

* Descargar la versión 11 del JDk segun el sistema operativo, para Windows ([lista de JDK](https://www.oracle.com/java/technologies/downloads/#java11-linux))

#### Configuracion de JDK:

* Debe contar con la version 11.0.13 o superior de maven ([como instalar](https://docs.oracle.com/en/java/javase/17/install/overview-jdk-installation.html)).

---

Ejemplo para levantar el api:

* Debemos de instalar algunas dependencias necesarias para el api con estos comandos:

    ```jshelllanguage
    $ mvn clean install
    $ java -jar .\target\sprint-boot-apirest-usuarios-1.0-SNAPSHOT.jar
    ```
---

### Comprobación del servicio API User (API Canal o de un solo squad)

#### mediante postman:
* Se debe importar las colecciones en el postman para golpear a los servicios:
  NISSUM.postman_collection.json


* 1ro. Para la obtención del token maestro se debe golpear al servicio: http://localhost:9091/bci-auth/api/login (API Multicanal)
* 2do. Para la creación de un usuario se debe golpear al servicio: http://localhost:9095/bci-user/api/user


* Obs: En está API se encuentra la validación del formato de correo y la contraseña.

#### verificación de data con H2:
* Los datos se almacenan en la API security

#### mediante swagger:
* http://localhost:9095/bci-user/swagger-ui.html
* Authorize: obtener el token del servicio de login (1ro) usando postman ej. Bearer eyJhbGciOiJIUzUxMiJ9.eyJhdXRob3JpdGllcyI6Ilt7XCJhdXRob3JpdHlcIjpcIlJPTEVfQURNSU5cIn0se1wiYXV0aG9yaXR5XCI6XCJST0xFX1VTRVJcIn1dIiwic3ViIjoiYWRtaW4iLCJpYXQiOjE2NjUwNzI3MjcsImV4cCI6MTY2NTA4NzEyN30.g9sEaeIAz4Xx3n3P9_OYvx_mQyogEzK1ZLMMFqDWbR3mCWDCIJmxjtLvkaXg1z63676msFLzDioJGkImPOMDww
* Body: recoger la petición del servicio crear usuario (2do) modificando al menos los campos username, email y password
* Execute


***

## Tecnologías Utilizadas

![Java](https://cdn.static.innovacionpacifico.com/document_library/readme/java-logo-64.png) [JDK](https://www.oracle.com/technetwork/java/index.html)
![Spring Boot](https://cdn.static.innovacionpacifico.com/document_library/readme/spring-boot-logo-64.png) [Spring Boot](https://spring.io/projects/spring-boot)
![Maven](https://cdn.static.innovacionpacifico.com/document_library/readme/maven-logo-64.png) [Maven](https://maven.apache.org/)
![Rest](https://cdn.static.innovacionpacifico.com/document_library/readme/rest-logo-64.png) [Rest](https://es.wikipedia.org/wiki/Transferencia_de_Estado_Representacional)

***