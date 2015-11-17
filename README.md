# Docker Microservice Playground
My playground for trying out frameworks and technologies related to modern java-based web applications. It revolves around:

- RESTful Web-Service using JAX-RS and Swagger
- Microservices with Dropwizard
- MongoDB
- Running the Application in a Docker Container
- Single Page Applications with AngularJS
- Yeoman, Bower, Grunt

# Running the Application
You need to have Docker up and running to run the ProZu service. After checking out the projekt, move to the folder prozu-service and execute:
````bash
mvn pre-integration-test
````
This builds the whole project and starts both the MongoDB and the ProZu service in separate docker containers. If you just want to start the docker container use
````bash
mvn docker:start
````
In order to stop both containers use
````bash
mvn docker:stop
````
Finally, to run the whole build (including tests) and to push the created image to docker hub, use 
````
mvn -Ddocker.username=<dockerHubUser> -Ddocker.password=<dockerHubPassword> deploy
````
