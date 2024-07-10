FROM openjdk:17-jdk-alpine
WORKDIR /app
COPY target/artedesejo-0.0.1-SNAPSHOT.jar artedesejo-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "artedesejo-0.0.1-SNAPSHOT.jar"]