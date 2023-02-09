How to run application:
For this application you need to use Docker

1 First step after cloning project from gitlab you must build multi-stage image using Maven!
Run this command in terminal:


```
mvn docker:build
```

2 Next step you can make when build was finished and run next command in terminal for start docker-compose deploying:

```
docker-compose up
```