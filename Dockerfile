FROM maven:3.6.0-jdk-11-slim AS build

WORKDIR /

COPY src /home/app/src

COPY pom.xml /home/app

RUN mvn -f /home/app/pom.xml clean package

FROM amazoncorretto:11.0.15

COPY --from=build /home/app/target/serasa-java-11-jr-0.0.1-SNAPSHOT.jar /usr/local/lib/app.jar

EXPOSE 80 8080

ENTRYPOINT ["java","-jar","/app.jar"]