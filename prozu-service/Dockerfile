# please note: start the mongodb container before

# docker images
# docker rmi -f java:8-jre
# docker run -it java:8-jre java -version
# docker run -it java:8-jre //bin/bash
# docker build -t phauer/prozu-service .
# docker run -it phauer/prozu-service //bin/bash

FROM java:8-jre
ADD target/prozu-service-0.0.1-SNAPSHOT.jar /data/prozu-service.jar
ADD src/main/resources/config.yml /data/config.yml
RUN java -jar /data/prozu-service.jar server /data/config.yml
EXPOSE 8080