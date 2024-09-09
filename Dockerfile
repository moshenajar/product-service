FROM openjdk:17-jdk-slim AS build

COPY pom.xml mvnw ./
COPY .mvn .mvn
RUN ./mvnw dependency:resolve

COPY src src
RUN ./mvnw package

FROM openjdk:17-jdk-slim
WORKDIR demo
COPY --from=build target/*.jar product-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "product-service-0.0.1-SNAPSHOT.jar"]