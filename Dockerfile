FROM openjdk:11-jre-slim

COPY build/libs/doit-api-0.1.0.jar /app.jar

EXPOSE 8080

ENTRYPOINT exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
