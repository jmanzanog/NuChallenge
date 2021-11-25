FROM gradle:7.1-jdk11-hotspot AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon
FROM openjdk:11-jre-slim
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/NuChallenge-1.0-all.jar /app/NuChallenge-1.0-all.jar
ENTRYPOINT ["java","-jar","/app/NuChallenge-1.0-all.jar"]
