# platform-application
A web application consisting of an Angular frontend, Spring Boot REST API backend and AWS API's. 

## Prerequisites: 
* Download and install docker-compose
* Download and move to 'platform-application'
<br>`$ git clone https://github.com/kmponis/platform-application.git` 

## Deploy locally with docker-compose 
* Enter 'y' when deploying first time
<br>`$ docker-compose up`

## Test platform-application, frondend and backend
* Users: admin:admin, user:user
<br>`$ open http://<docker_ip_address>/`
> Note: On first attempt it will take 5-6 seconds to login due to AWS Lambda cold start.
<br> The session time-out is set to 5 min so after that the user will be logged out.
* Test platform_be endpoint on swagger
<br>`$ open http://<docker_ip_address>:8885/swagger-ui.html`