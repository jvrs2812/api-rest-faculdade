FROM arm64v8/openjdk:19-jdk-slim-buster

COPY target/rest-0.0.1-SNAPSHOT.jar /app/rest.jar

WORKDIR /app

CMD ["java", "-jar", "rest.jar"]
