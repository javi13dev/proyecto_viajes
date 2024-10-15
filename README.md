# PROYECTO DE VIAJES

- Cuatro microservicios que exponen diferentes recursos para la realización de reservas y consulta de las mismas. Los microservicios están desarrollados con **Spring Boot**, se registran y conectan mediante **Eureka Server**, y el consumo de recursos se realiza a través de descubrimiento de servicios. El cliente accede a estos recursos a través de un **API Gateway**, que abstrae la ubicación de los microservicios, simplificando su consumo.


## Test microservicios

- **1**: Crear una red que compartirán los microservicios:
```bash
docker network create mi-red
```
- Todos los servicios están dockerizados y con su correspondiente imagen
- https://hub.docker.com/r/javi13dev/viajes/tags
- https://hub.docker.com/r/javi13dev/mimysql/tags
- **2**: Sustituir la ip que figura en el archivo docker-compose.yml por la ip del host donde se realice la prueba. 
- **3**: Ejecutar el docker-compose.yml mediante:
```bash
docker compose up
```
- **4**: Probar en postman una petición get a las reservas ya realizadas por client1: http://localhost:11000/sreserva/reservas/reserva/client1
- Obtiene las reservas en formato Json.

---

- O bien:
  
- **1** : Iniciar el contenedor de la base de datos en /bbdd de acuerdo a la guía Imagen_contenedor_bbdd.txt.
- **2** : Generar para cada servicio el .jar, situándose en el directorio del servicio:
-  mvn clean package
-  El .jar generado en /target del servicio se moverá a su directorio correspondiente en /docker
-  Renombrar el .jar de acuerdo a su Dockerfile correspondiente, por ejemplo, microvuelos.jar
- **3** : Adaptar la variable de entorno IP_HOST a la ip correspondiente donde se vaya a ejecutar. 
- **4** : Levantar todos los contenedores mediante docker compose up


## Tecnologías Utilizadas

- **Java**: El lenguaje de programación en el que están implementados los microservicios.
- **Spring Boot**: Framework utilizado para construir los microservicios, facilitando la creación de aplicaciones Java robustas y de alto rendimiento.
- Para la autenticación y autorización se implementará mediante **Spring security**
- **Eureka Server**: Servicio de descubrimiento que permite a los microservicios registrarse y encontrarse entre sí.
- **Docker**: Utilizado para contenerizar los microservicios, asegurando que puedan ejecutarse de manera consistente en diferentes entornos.
  
## Arquitectura del Proyecto

- **Microservicios**: Cada uno de los microservicios expone recursos específicos y se comunica con los demás a través de Eureka.
- **Gateway**: Un API Gateway centraliza el acceso a los microservicios, proporcionando una única entrada para el cliente.
- **Eureka Server**: Actúa como un registro donde todos los microservicios se registran y desde donde pueden ser descubiertos.
- **Docker**: Cada microservicio se empaqueta en un contenedor Docker, lo que facilita su despliegue y escalado.
- Microservicio hoteles puerto:8000
- Microservicio clientes puerto:8500
- Microservicio vuelos puerto:9000
- Microservicio reservas puerto:9500
- Eureka server puerto:8761
- Gateway puerto:11000


### Dependencias Iniciales que comparten los microservicios
```xml
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-devtools</artifactId>
        <scope>runtime</scope>
        <optional>true</optional>
    </dependency>
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```


## 1) Microservicio de Hoteles

Este microservicio se conecta a la base de datos y expone la siguiente información de acceso libre:

### Recursos Expuestos
- **Datos de hotel por identificador**
  - Ejemplo: `GET http://localhost:11000/shotel/hoteles/hotel/5`
- **Lista de hoteles por localización**
  - Ejemplo: `GET http://localhost:11000/shotel/hoteles/hoteles/Madrid`



## 2) Microservicio de Clientes

### Recursos Expuestos

- **Devolución de un Cliente a partir del usuario**
- **Devolución de un Cliente a partir de la combinación usuario y contraseña**
- **Registro de un Cliente a partir de los datos del mismo**


### Ejemplos de Uso

- **Devolución de un Cliente por Usuario**

  - URL: `http://localhost:11000/scliente/clientes/cliente/client1`

- **Devolución de un Cliente a partir de Usuario y Contraseña**

  - URL: `http://localhost:11000/scliente/clientes/cliente`
  - Ejemplo de petición (login):
  
    ```json
    {
        "usuario": "client1",
        "password": "client1"
    }
    ```

  - Ejemplo de respuesta (recuperación del usuario):
  
    ```json
    {
        "usuario": "client1",
        "password": "client1",
        "direccion": "C/ Asturias",
        "tarjeta": "112233223",
        "dni": "1234B"
    }
    ```

- **Registro de un Cliente**

  - URL: `http://localhost:11000/scliente/clientes/registro`
  - Ejemplo de cuerpo de petición:
  
    ```json
    {
        "usuario": "client3",
        "password": "client3",
        "direccion": "C/ Asturias",
        "tarjeta": "112233223",
        "dni": "1234B"
    }
    ```
## 3) Microservicio de Vuelos

### Recursos Expuestos

- **Lista de vuelos a partir de un destino y número de plazas**
- **Actualización de plazas a partir de identificador de vuelo y plazas reservadas** (Estará securizado)
- **Datos de Vuelo por identificador**

### Ejemplos de Peticiones

- **Datos de un Vuelo por Identificador**
  
  - URL: `http://localhost:11000/svuelo/vuelos/vuelo/1`

- **Lista de Vuelos por Destino y Número de Plazas**

  - URL: `http://localhost:11000/svuelo/vuelos/vuelos?destino=Paris&plazas=3`

## 4) Microservicio de Reservas

### Recursos Expuestos

- **Alta de una reserva con los datos correspondientes** (estará securizado)
- **Lista de reservas por cliente**

### Ejemplos de Peticiones

- **Alta de una Reserva**

  - URL: `http://localhost:11000/sreserva/reservas/reserva`
  - Ejemplo de cuerpo de la petición (POST):
  
    ```json
    {
        "idreserva": 0,
        "nombre": "Madrid",
        "idvuelo": 5,
        "usuario": "client1"
    }
    ```

- **Recuperación de Reservas por Cliente**

  - URL: `http://localhost:11000/sreserva/reservas/reserva/client1`
  - Ejemplo de respuesta (GET):
  
    ```json
    {
        "idreserva": 43,
        "nombre": "PanAir",
        "idvuelo": 5,
        "precio": 160.0,
        "usuario": "client1"
    }
    ```

