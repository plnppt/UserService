FROM openjdk:17

COPY ./build/libs/UserService-0.0.1-SNAPSHOT.jar ./server.jar

ENTRYPOINT ["java", "--enable-preview", "-jar", "server.jar"]