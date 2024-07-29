FROM openjdk:17-jdk-slim

MAINTAINER macoyt.com

COPY target/customers-1.0.0-SNAPSHOT.jar customers-1.0.0-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "customers-0.0.1-SNAPSHOT.jar"]

# create image
# docker build . -t macoyt/my-bank-customers:v0.0.1

#execute container in terminal - ports: 1 - local 2 - container network
# docker run -p 8100:8100 macoyt/my-bank-customers:v0.0.1

#execute container detached mode  - ports: 1 - local 2 - container network
# docker run -d -p 8100:8100 macoyt/my-bank-customers:v0.0.1

#execute docker stop image
# docker stop <containerId>

# conection with container terminal
# docker exec it <containerId> /bin/sh

#execute build image from pom.xml
# (root-ms) ./mvnw.cmd spring-boot:build-image

#execute build image google-jib
# ./mvnw compile jib:build

#

# docker compose up
# docker compose up -d
# docker compose down
# docker compose stop
# docker compose start


#  java -D spring.profiles.active=prod -jar ./target/accounts-0.0.2-SNAPSHOT.jar

# Set profile with environment variables
# $env:SPRING_PROFILES_ACTIVE="prod"; java -jar ./target/accounts-0.0.1-SNAPSHOT.jar

# Set profile with command line
# java -jar ./target/accounts-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod

#docker run --rm -d --name rabbitmq -p 5672:5672 -p 15672-15672 rabbitmq:3.13-management
# user and pass: guest