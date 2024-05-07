FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
EXPOSE 8080
COPY ./target/*.jar app.jar
COPY ./flag.txt flag.txt
ENTRYPOINT ["java","-jar","/app.jar"]