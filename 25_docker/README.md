How to run application:
For this application you need to use Docker

After cloning project from gitlab run next command in terminal for start docker-compose deploying:

```
docker-compose up
```

For build from docker you can use this command in Windows:

```
docker run --rm --name 25_docker -v %cd%:/app -w /app maven:3.6.3-jdk-11 mvn clean package
```

For build using Linux:

```
docker run --rm --name 25_docker -v "$(pwd)":/app -w /app maven:3.6.3-jdk-11 mvn clean package
```