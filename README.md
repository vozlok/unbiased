# Setup
Install `Java JDK 8`, `Apache Maven 3.3.9`

## To run tests and generate Allure report:

* run `mvn clean test`
* run `mvn site`

## To see a report (in local machine):

0. uncomment section jetty-maven-plugin in pom.xml
1. run `mvn jetty:run`
2. open `http://localhost:8080` in your browser

