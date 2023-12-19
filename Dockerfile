FROM openjdk:11
WORKDIR /app
EXPOSE 8090
COPY ./target/*.jar ./app.jar
ENTRYPOINT java -jar app.jar