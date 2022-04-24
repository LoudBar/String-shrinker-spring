FROM maven:3.8.1-openjdk-11-slim as build
COPY src /home/application/src
COPY pom.xml /home/application/
USER root
RUN --mount=type=cache,target=/root/.m2 mvn -DskipTests=true -f /home/application/pom.xml clean package

FROM openjdk:11-jre-slim
COPY --from=build /home/application/target/application.jar /usr/local/lib/application.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/application.jar"]
