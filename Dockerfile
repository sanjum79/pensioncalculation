FROM openjdk:8-jdk-alpine
ADD target/pensioncalculation-1.0.0.jar pc.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/urandom","-Dspring.profiles.active=container","-jar","pc.jar"]