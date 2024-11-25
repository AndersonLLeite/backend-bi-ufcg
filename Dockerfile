# Estágio 1: Build
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
RUN mvn clean package -DskipTests

# Estágio 2: Execução
FROM openjdk:17-jdk-slim
WORKDIR /app
EXPOSE 8080
COPY --from=build /app/target/bi-ufcg-backend-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
