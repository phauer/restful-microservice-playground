# please note: start the mongodb container before

# docker images
# docker rmi -f java:8-jre
# docker run -it java:8-jre java -version
# docker run -it java:8-jre //bin/bash
# docker build -t phauer/prozu-service .
# docker run -p 8080:8080 -it phauer/prozu-service //bin/bash
# docker run -p 8080:8080 -it --link prozu-mongodb phauer/prozu-service //bin/bash
# let's start without links and let the contains communicate via ports
# docker run -p 8080:8080 -it phauer/prozu-service //bin/bash
# -it ->  attach the console to the process’s standard input, output, and standard error
# docker run -p 8080:8080 -it phauer/prozu-service

FROM java:8-jre
ADD target/prozu-service-0.0.1-SNAPSHOT.jar /data/prozu-service.jar
ADD src/main/resources/docker-config.yml /data/docker-config.yml
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/data/prozu-service.jar", "server", "/data/docker-config.yml"]
