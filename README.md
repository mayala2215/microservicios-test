# Proyecto de Microservicios  (Clientes y Cuentas)

**Autor:** Moisés Ayala  
**Lenguaje:** Java 21  
**Framework:** Spring Boot 3.5.7  
**Base de datos:** MySQL 8.0  
**Mensajería:** RabbitMQ  
**Orquestación:** Docker Compose  

---

## Descripción General

Este proyecto demuestra una arquitectura basada en **microservicios** para la gestión de clientes y cuentas bancarias.  
Se implementan dos servicios independientes que se comunican entre sí mediante **RabbitMQ**, mientras ambos comparten una instancia de **MySQL** con bases separadas.

El sistema levanta automáticamente las bases de datos con datos iniciales a partir de un script SQL (`Backup.sql`).

---

## 🧩 Arquitectura del Proyecto

* microservicios-test/
  * cliente-service/          # Microservicio de clientes
    * src/
    * Dockerfile
  * cuenta-service/           # Microservicio de cuentas
    * src/
    * Dockerfile
  * initdb/
    * Backup.sql              # Script de inicialización de datos y estructuras
  * docker-compose.yml        # Orquestación de servicios



---

## Instrucciones para Levantar el Proyecto

### Requisitos previos
- Tener **Docker** y **Docker Compose** instalados.  
- Puertos disponibles: **3307**, **8081**, **8082**, **5672**, y **15672**.  

---

### Ejecución

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/mayala2215/microservicios-test.git
   cd microservicios-test/

2. **Levantar contenedores:**
   ```bash
   docker compose up --build



3. **Collection Postman**

    El proyecto incluye una colección de Postman llamada MicroServicesTest.postman_collection.json, la cual contiene todas las peticiones necesarias para probar los microservicios de Clientes y Cuentas.

    Estructura de la colección

   **clientes**

    - POST crearCliente → Crea un nuevo cliente y envía un evento CREAR a RabbitMQ.

    - PUT actualizarCliente → Actualiza la información de un cliente existente y envía un evento ACTUALIZAR.

    - DEL eliminarCliente → Elimina un cliente por ID.

    - GET listarCliente → Obtiene todos los clientes registrados.

    - GET listarClienteById → Busca un cliente específico por su ID.

    **cuentas**

    - GET listarCuentas → Lista todas las cuentas registradas.

    - GET cuentaByNombreCuenta → Busca una cuenta por nombre de cliente.

    - GET cuentaByNumeroCuenta → Consulta una cuenta específica por número.

    **movimientos**

    - POST agregarMovimiento → Registra un movimiento (depósito o retiro) en una cuenta.

    **reportes**

    - GET generarReporteRangoByNombreCuenta → Genera un reporte de movimientos dentro de un rango de fechas.




  

