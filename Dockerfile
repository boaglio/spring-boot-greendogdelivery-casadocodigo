FROM openjdk:17
MAINTAINER boaglio.com
COPY target/springboot-greendogdelivery-*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]