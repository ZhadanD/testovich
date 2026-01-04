FROM gradle:8.5-jdk21 AS builder

WORKDIR /app

COPY . .

RUN gradle bootJar

FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8090

ENTRYPOINT [ "java", "-jar", "app.jar", "--spring.profiles.active=docker" ]