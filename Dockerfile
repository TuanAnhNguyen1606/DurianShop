FROM eclipse-temurin:21-jre-alpine
COPY target/EXE201-0.0.1-SNAPSHOT.jar EXE201-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/EXE201-0.0.1-SNAPSHOT.jar"]