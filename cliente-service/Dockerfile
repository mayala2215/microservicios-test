# Etapa 1: compilar el proyecto
FROM maven:3.9.9-eclipse-temurin-21 AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn dependency:purge-local-repository -DreResolve=true -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.insecure=true

COPY src ./src

RUN mvn clean package -DskipTests -Dmaven.wagon.http.ssl.allowall=true -Dmaven.wagon.http.ssl.insecure=true

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
RUN addgroup -S spring && adduser -S spring -G spring
USER spring

COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]
