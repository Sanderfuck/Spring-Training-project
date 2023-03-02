FROM maven:3.8.2-jdk-11 as builder
COPY . .
RUN mvn clean package -DskipTests

FROM tomcat:9.0-jdk11-openjdk
COPY --from=builder /target/19_jpa_hibernate-1.0.0-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
CMD ["catalina.sh", "run"]
