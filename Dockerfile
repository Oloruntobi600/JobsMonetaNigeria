FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /usr/src/JobsMonetaNigeria

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /JobsMonetaNigeria

COPY --from=build /usr/src/JobsMonetaNigeria/target/JobsMonetaNigeria-0.0.1-SNAPSHOT.jar JobsMonetaNigeria.jar

ENTRYPOINT ["java","-jar","JobsMonetaNigeria.jar"]