# Alpine because it's lighter
FROM openjdk:8-jdk-alpine
MAINTAINER Wendell Adriel <wendelladriel.ti@gmail.com>

# Add JAR file and run it as entrypoint
ADD target/api-gateway.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]

# Expose the port
EXPOSE 8765

# rebase -i; cherrypick
# SnAyy23vQm37MaeHoZ