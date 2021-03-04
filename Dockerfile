FROM amazoncorretto:11-alpine-jdk
ADD build/libs/detector-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
CMD java -jar detector-0.0.1-SNAPSHOT.jar
