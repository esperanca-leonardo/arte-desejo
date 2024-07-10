FROM openjdk:17-jdk-alpine
EXPOSE 8080
WORKDIR /app
COPY target/artedesejo-0.0.1-SNAPSHOT.jar /app/artedesejo-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar", "artedesejo-0.0.1-SNAPSHOT.jar"]