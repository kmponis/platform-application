### STAGE 1: Build ###
FROM maven:3.5-jdk-8-alpine AS build
WORKDIR /workspace
# Create Dependency Layer, so maven dependencies will not be 
# downloaded each time executing: $docker-compose up --build 
ADD pom.xml /workspace
RUN mvn verify clean --fail-never
# Build application
COPY . /workspace
RUN mvn clean install

### STAGE 2: Deploy ###
FROM openjdk:8-jre-alpine as deploy
COPY --from=build /workspace/target/platform-be.jar .
EXPOSE 8885
ENTRYPOINT ["java","-jar","platform-be.jar"]