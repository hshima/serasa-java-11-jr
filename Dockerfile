FROM maven:3.8.5-jdk-11-slim AS build

WORKDIR /home

COPY .mvn/ .mvn

COPY mvnw pom.xml ./

RUN ./mvnw dependency:resolve

#RUN mvn -f /home/pom.xml clean package

CMD ["./mvnw", "spring-boot:run"]

FROM amazoncorretto:11.0.15

WORKDIR /home

COPY . /home

EXPOSE 80 8080

CMD cd target

#ENTRYPOINT ["java","-jar","serasa-java-11-jr-0.0.1-SNAPSHOT.jar"]
CMD ["./mvnw", "spring-boot:run"]