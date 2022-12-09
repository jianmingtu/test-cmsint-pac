#FROM openjdk:11-jre-slim

FROM openjdk:18.0.2.1-slim

COPY ./target/cmsint-pac-transformer.jar cmsint-pac-transformer.jar

ENTRYPOINT ["java", "-jar","/cmsint-pac-transformer.jar"]
