#docker run --name prozu-mongodb -d -P phauer/mongodb
#docker run --name prozu-mongodb -d phauer/mongodb
# name ist wichtig für späteren link
# -d -> run container in background
# -P -> map any required network port inside our container to our host (achtung: wird zufälliger port auf host system) -> explizit mit -p 123:123 setzen
#docker ps 
#docker kill <containerid>

# If using boot2docker
# Usage: mongo --port <port you get from `docker ps`>  --host <ip address from `boot2docker ip`>
# richtigen port nehmen, achte auch port forwarding "0.0.0:32770->27017/tcp" -> use 32770
# wie boot2docker ip rausbekommen? a) steht beim starten der docker shell b) >docker-machine ip <machine name> (machine name ist "default" - einfach die VirtualBox GUI starten)

# mongo --port 32770 --host 192.168.99.100

# https://hub.docker.com/_/mongo/
# https://docs.docker.com/examples/mongodb/
FROM mongo:2.6.11