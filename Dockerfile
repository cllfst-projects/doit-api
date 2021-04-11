FROM openjdk:11-jre-slim

ARG JAR_NAME

COPY ${JAR_NAME} /app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", \
        "exec java -Djava.security.egd=file:/dev/./urandom -jar /app.jar"]