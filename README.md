# Vanilla Kotlin “Reactive” with Spring WebFlux

This project aims to show how to build a reactive microservice using Coroutines and Spring Webflux.

The repository has 5 branches:

* master: The branch with documentation about the example and the playground used for test.
* springmvc: the basic application made with Spring MVC
* springwebflux: the first reactive version using Spring WebFlux and Project Reactor. 
* flow: the last reactive version using Coroutines.
* flowrsocket: RSocket support


To run one of the provided sample applications please move to playground folder and run:

	$ java -Dcom.sun.management.jmxremote -Dcom.sun.management.jmxremote.port=1617 -Dcom.sun.management.jmxremote.authenticate=false -Dcom.sun.management.jmxremote.ssl=false -jar flow.jar

Replace flow.jar with the microservice version that you would like to run.


In order to execute the performance tests, please open playground folder and run:

	$ test.sh reportFolderName

Remember to set JMETER_HOME environment variable before running tests.

To test the SSE execute a command like:

    $ curl "Accept:text/event-stream" https://localhost:8080/feed



