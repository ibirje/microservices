# Alpine because it's lighter
FROM openjdk:17-jdk-alpine
MAINTAINER Wendell Adriel <wendelladriel.ti@gmail.com>

# Add JAR file and run it as entrypoint
ADD target/category-service-1.0.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]