FROM gradle:8.5.0-jdk17 AS BUILD
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM openjdk:17-ea-slim

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/ /app/

CMD ["java", "-jar", "/app/blog-0.0.1-SNAPSHOT.jar"]
