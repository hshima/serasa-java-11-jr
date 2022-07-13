FROM amazoncorretto:11.0.15

WORKDIR /

COPY target/*.jar app.jar

EXPOSE 80 8080

ENTRYPOINT ["java","-jar","/app.jar"]