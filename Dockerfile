FROM openjdk:17-alpine

COPY target/*.jar /home/app/Wigellsushi.jar

ENTRYPOINT [ "java", "-jar", "/home/app/Wigellsushi.jar" ]