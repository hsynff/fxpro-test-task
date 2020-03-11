**Building from source**
----------
Prerequisites:
* JDK (>1.8)
* Maven

After cloning the repo:

`cd fxpro-test-task`

`mvn clean compile assembly:single`

Executable jar must be created in the target folder:

`cd ./target/`

**Running from executable jar**
-----------
Prerequisites:
* JRE (>1.8)

After cloning the repo find executable jar under following folder:

`cd fxpro-test-task/exec`

**Manual**
----------
For running application with default mock inputs pass nothing as arguments:

`java -jar <JAR_NAME>.jar`

For running application with your input pass arguments as space separated values:

`java -jar <JAR_NAME>.jar 3 1 1 4 5 6 7`

