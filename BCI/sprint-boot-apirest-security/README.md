# API security - BCI

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
    $ java -jar .\target\sprint-boot-apirest-security-1.0-SNAPSHOT.jar
    ```

* Se debe importar las colecciones en el postman para golpear a los servicios:
  NISSUM.postman_collection.json


* Se debe importar las colecciones en el postman para golpear a los servicios.

Servicio para obtención del Token:
* POST: http://localhost:9091/bci-auth/api/login
* username:admin
* password:12345

***

## Tecnologías Utilizadas

![Java](https://cdn.static.innovacionpacifico.com/document_library/readme/java-logo-64.png) [JDK](https://www.oracle.com/technetwork/java/index.html)
![Spring Boot](https://cdn.static.innovacionpacifico.com/document_library/readme/spring-boot-logo-64.png) [Spring Boot](https://spring.io/projects/spring-boot)
![Maven](https://cdn.static.innovacionpacifico.com/document_library/readme/maven-logo-64.png) [Maven](https://maven.apache.org/)
![Rest](https://cdn.static.innovacionpacifico.com/document_library/readme/rest-logo-64.png) [Rest](https://es.wikipedia.org/wiki/Transferencia_de_Estado_Representacional)

***