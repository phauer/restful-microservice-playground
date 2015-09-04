# Restful Microservice Playground
My playground for trying out frameworks and technologies related to modern java-based web applications. It revolves around:

- RESTful Web-Service using JAX-RS and Swagger
- Microservices with Dropwizard
- MongoDB
- Running the Application in a Docker Container
- Single Page Applications with AngularJS
- Yeoman, Bower, Grunt

# Getting Started
You need to have Docker up and running to run the application. Move to the folder prozu-service and execute:
````bash
mvn docker:start
````
This starts both the MongoDB and the ProZu-Service in separate docker containers.

````bash
mvn docker:stop
````
Stops both containers.