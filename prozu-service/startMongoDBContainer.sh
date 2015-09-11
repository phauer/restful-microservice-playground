#!/bin/bash

# starts a container with mongodb
# no local installation of mongodb needed

docker run -d -p 27017:27017 mongo:2.6.11 --smallfiles
