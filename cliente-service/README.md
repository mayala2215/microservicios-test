# Cliente Service

**Autor:** Moisés Ayala  
**Lenguaje:** Java 21  
**Framework:** Spring Boot 3.5.7  
**Base de datos:** MySQL  
**Mensajería:** RabbitMQ  

---

## Descripción

El microservicio **Cliente Service** se encarga de gestionar la información de los **clientes bancarios**.  
Además de manejar el CRUD de clientes, este servicio **envía mensajes a RabbitMQ** cada vez que se realiza una acción relevante.

**Principales funciones:**

- **CREAR:** cuando se registra un nuevo cliente.  
- **ACTUALIZAR:** cuando se modifica la información de un cliente existente.  

Estos eventos son consumidos por otros servicios (como `cuenta-service`) para mantener los datos sincronizados, logrando una **arquitectura desacoplada basada en eventos**.
