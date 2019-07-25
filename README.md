# sample-gradle-docker-nexus

## Purpose

Question: Is it possible to run a local instance of Nexus in a docker container and then upload a .jar file into it via Gradle?

Answer: Yes! We can run a local Nexus docker container and then run a gradle command to upload a jar 
* Need to use the maven plugin in buid.gradle as well as the uploadArchives script
* Note that Nexus will run on port 8091 but this can be changed in the docker-compose.yml file

## Instructions

### Running Nexus Locally
In a real software environment, there is an assumption that a Nexus instance is up and running somewhere.

As there is no Nexus instance for this project, we will have to manually run a local Docker container which will run Nexus locally.

In the root directory of this project run the following command to run Nexus locally inside a Docker container:
```
docker-compose up
```
Using your web browser, you can now navigate to the URL below (Username + Password provided):
```
http://localhost:8091/nexus/
Username: admin
Password: admin123
```

### Building Jar
Before we can upload a .jar to Nexus, we need to build it.

Navigate to the root directory of this project and run the command below to run all the tests and build the jar:
```
./gradlew clean build
```
Let's quickly test the jar before we think about uploading it. 
From the root directory of this prohect, run the command below to run the application:
```
java -jar build/libs/<name_of_jar>.jar
```
Run the following curl to prove that the application is up and running. You can also navigate to the URL below using a web browser:
```
curl http://localhost:8080/hello/batman
```
Should see the following output (this also proves that we have a .jar file that has built without issues and the API inside it works):
```
hello batman
```

### Upload Jar to Nexus
Let's upload a jar to our local Nexus instance. 

We will run the uploadArchives task. This will look for all files inside the build directory and upload it.

From the root directory of the project, run the following command:
```
./gradlew uploadArchives -info
```
Expected output:
```
➜  sample-docker-nexus git:(master) ./gradlew uploadArchives -info
...
...
> Task :uploadArchives
Task ':uploadArchives' is not up-to-date because:
  Task has not declared any outputs despite executing actions.
Publishing configuration: configuration ':archives'
Publishing to org.gradle.api.publication.maven.internal.deployer.DefaultGroovyMavenDeployer@207f9261
Deploying to http://localhost:8091/nexus/content/repositories/snapshots/
Downloading: com/thetestroom/sample-docker-nexus/1.0-SNAPSHOT/maven-metadata.xml from repository remote at http://localhost:8091/nexus/content/repositories/snapshots/
Transferring 0K from remote
Uploading: com/thetestroom/sample-docker-nexus/1.0-SNAPSHOT/sample-docker-nexus-1.0-20190725.092425-4.jar to repository remote at http://localhost:8091/nexus/content/repositories/snapshots/
Transferring 16376K from remote
Uploaded 16376K
....
....

BUILD SUCCESSFUL in 1s
```
In order to see the uploaded .jar, using your web browser navigate to:
```
http://localhost:8091/nexus/content/repositories/snapshots/com/thetestroom/
```