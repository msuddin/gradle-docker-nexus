# sample-java-spring-boot-web-api

## Purpose

Question: Is it possible to run a local instance of Nexus in a docker container and then load a .jar file into it?

Answer:
* 

## Instructions
### Building Jar
Navigate to the root directory of this project and run the command below to run all the tests and build the jar:
```
./gradlew clean build
```
Now run the command below to run the application:
```
java -jar build/libs/<name_of_jar>.jar
```
The application should be accessible on the following url:
```
http://localhost:8080/
```
You can pass in a name on the url which should display the same name on the browser:
```
// Set in the browser URL window:
http://localhost:8080/hello/batman

// Should see diplayed in the browser window:
hello batman
```
### Uploading Jar to Nexus

