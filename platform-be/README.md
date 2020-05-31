# platform-be REST API
A Spring Boot REST API to wrap up all(AWS lambda) REST API's 

## Prerequisites: 
* Download and install docker
* Download and move to 'platform-be'
<br>`$ git clone https://github.com/kmponis/platform-application.git`

## Dependency Check
* Run dependency checker
<br>`$ mvn test org.owasp:dependency-check-maven:check`
* Check the OWASP DependencyCheck report
<br>`$ open target/dependency-check-report.html`

## Deploy locally 
### With Java
* Build and Deploy
<br>`$ mvn clean install`
<br>`$ java -jar target/platform-be.jar`

### With Docker 
* Build and Deploy
<br>`$ docker build -t platformbeimage -f Dockerfile .`
<br>`$ docker run -p 8885:8885 platformbeimage`

## Test platform-be
* Test platform_be endpoint on swagger
<br>`$ open http://<docker_ip_address>:8885/swagger-ui.html`
> Note: On first attempt it will take 5-6 seconds to response due to AWS Lambda cold start.
* Check the jacoco reports
<br>`$ open target/jacoco-reports/index.html`

## Generic
### Upload to dockerhub for external use
* A dockerhub user is required
<br>`$ docker tag platformbeimage <your username>/platform-be-image:latest`
<br>`$ docker login`
<br>`$ docker push <your username>/platform-be-image:latest`