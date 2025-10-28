# Cliente Service

**Autor:** Moisés Ayala  
**Lenguaje:** Java 21  
**Framework:** Spring Boot 3.5.7  
**Base de datos:** MySQL  
**Mensajería:** RabbitMQ  

---

## Descripción

El microservicio **Cliente Service** se encarga de gestionar la información de los clientes bancarios.  
Al crear un nuevo cliente, este servicio envía un mensaje a **RabbitMQ**, notificando a otros servicios (como `cuenta-service`) que se ha creado un nuevo registro.  

Esto permite que el sistema mantenga sincronizados los datos de clientes y cuentas en una arquitectura desacoplada basada en eventos.