FROM openjdk:17-jdk-slim

MAINTAINER macoyt.com

COPY target/accounts-1.0.0-SNAPSHOT.jar accounts-1.0.0-SNAPSHOT.jar

FROM eclipse-temurin@sha256:b9f5c0acd03eb8275690fe310506f62743a5aaf90e37d165bc0484037a5a9376

ENTRYPOINT ["java", "-jar", "accounts-1.0.0-SNAPSHOT.jar"]