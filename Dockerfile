FROM eclipse-temurin:21-jdk-jammy

RUN groupadd -r nonroot && useradd -r -g nonroot -m nonroot

WORKDIR /home/nonroot/application

COPY /target/app.jar ./app.jar

USER nonroot

CMD ["java", "-Dfile.encoding=UTF-8", "-agentlib:jdwp=server=y,transport=dt_socket,address=9000,suspend=n", "-jar", "./app.jar"]
