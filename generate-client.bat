rem how to get swagger.json?
rem a) start service and use http://localhost:8080/swagger.json
rem b) build service project and use .\prozu-service\target\swagger\swagger-ui\swagger.json

SET "FILENAME=%~dp0\swagger-codegen-cli.jar"
SET "PROJECTNAME=prozu-service-client"

rd /s /q "%PROJECTNAME%"	

if not exist "%FILENAME%" (
	bitsadmin /transfer "Downloading swagger-codegen jar" http://search.maven.org/remotecontent?filepath=io/swagger/swagger-codegen-cli/2.1.2/swagger-codegen-cli-2.1.2.jar "%FILENAME%"
) 

echo "you have to start the service first for accessing the swagger.json"
java -jar "%FILENAME%" generate -i ./prozu-service/target/swagger/swagger-ui/swagger.json -l java -c ./generate-client-config.json -o ./%PROJECTNAME%

cd ./%PROJECTNAME%
mvn install

pause