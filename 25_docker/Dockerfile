FROM maven:3.8.2-jdk-11 as builder
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:11
WORKDIR /app
COPY --from=builder /target/25_docker-1.0.0-SNAPSHOT.jar app.jar
COPY --from=builder /src/main/resources/sql/. /sql/
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
