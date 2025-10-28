# Cuenta Service

**Autor:** Moisés Ayala  
**Lenguaje:** Java 21  
**Framework:** Spring Boot 3.5.7  
**Base de datos:** MySQL  
**Mensajería:** RabbitMQ  

---

## Descripción


El microservicio **Cuenta Service** gestiona toda la información relacionada con **cuentas bancarias y movimientos financieros**.  
Forma parte de una arquitectura basada en microservicios y se comunica de manera **asíncrona** con otros servicios a través de **RabbitMQ**.

**Principales funciones:**
- Crea automáticamente una cuenta bancaria cuando recibe un evento `CREAR` desde `cliente-service`.
- Actualiza la información de una cuenta cuando recibe un evento `ACTUALIZAR`.
- Permite registrar y consultar los movimientos.