# Multi-staged Dockerfile
# With this setup, the application is built with the JDK first and then when running it, only the necessary files and the JRE is included in the container
# This reduces the size of the container and makes it more secure

# Taken from: https://codefresh.io/docs/docs/learn-by-example/java/gradle/

# Build stage

FROM gradle:7.5.1-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Run stage
FROM eclipse-temurin:11-jre

RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/pwmanager-0.0.1-SNAPSHOT.jar /app/spring-boot-application.jar

#ENTRYPOINT ["java", "-XX:+UnlockExperimentalVMOptions", "-XX:+UseCGroupMemoryLimitForHeap", "-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /app/spring-boot-application.jar ${0} ${@}"]
