# docker build -t phauer/prozu-service .
# docker run -p 8080:8080 -d phauer/prozu-service

FROM java:8-jre
ADD target/prozu-service-0.0.1-SNAPSHOT.jar /data/prozu-service.jar
ADD src/main/resources/docker-config.yml /data/docker-config.yml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/data/prozu-service.jar", "server", "/data/docker-config.yml"]
# use CMD[] to provide run arguments (like ENTRYPOINT), but they can be overridden by the client calling 'docker run'