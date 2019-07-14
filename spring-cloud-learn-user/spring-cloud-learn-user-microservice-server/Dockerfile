FROM java:8
EXPOSE 8080
ARG JAR_FILE
ADD target/${JAR_FILE} /spring-cloud-learn-user-microservice-server.jar
ENTRYPOINT ["java", "-jar","/spring-cloud-learn-user-microservice-server.jar"]