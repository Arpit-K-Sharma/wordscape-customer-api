# Build stage
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY ERP_V2/pom.xml .
COPY ERP_V2/src ./src
RUN mvn clean package -DskipTests

# Run stage
FROM --platform=linux/amd64 openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","app.jar"]