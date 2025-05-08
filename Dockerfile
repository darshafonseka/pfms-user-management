#start with a base image
FROM openjdk:21-jdk-slim

#information around who maintain the image
LABEL maintainer="pfms"

#Add the application's jar to the container
COPY target/user-management-0.0.1-SNAPSHOT.jar user-management-0.0.1-SNAPSHOT.jar

#Excecute the application
ENTRYPOINT ["java","-jar","/user-management-0.0.1-SNAPSHOT.jar"]


