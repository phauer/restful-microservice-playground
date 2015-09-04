# docker build -t phauer/prozu-service .
# docker run -p 8080:8080 -v /c/Development/logs://logs -d phauer/prozu-service
# volume share doesn't work on windows/boot2docker this way (https://github.com/boot2docker/boot2docker#folder-sharing)

FROM java:8-jre
ADD target/prozu-service-0.0.1-SNAPSHOT.jar /data/prozu-service.jar
ADD src/main/resources/docker-config.yml /data/docker-config.yml
RUN mkdir /logs
VOLUME /logs
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/data/prozu-service.jar", "server", "/data/docker-config.yml"]
# use CMD[] to provide run arguments (like ENTRYPOINT), but they can be overridden by the client calling 'docker run'