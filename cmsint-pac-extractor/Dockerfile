#FROM openjdk:11-jre-slim

FROM openjdk:18.0.2.1-slim

COPY ./target/cmsint-pac-extractor.jar cmsint-pac-extractor.jar

ENTRYPOINT ["java", "-jar","/cmsint-pac-extractor.jar"]
