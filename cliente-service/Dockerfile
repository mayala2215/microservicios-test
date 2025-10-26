# Etapa 1: compilar el proyecto
FROM eclipse-temurin:21-jre-alpine3.20
RUN apk add --no-cache maven
WORKDIR /app

COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: imagen ligera para ejecutar
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

# Crear usuario no root
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
