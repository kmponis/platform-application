# platform-ui
An AngularJS ui to implement platform's front-end.

## Prerequisites: 
* Download and install docker
* Download and move to 'platform-ui'
<br>`$ git clone https://github.com/kmponis/platform-application.git`
* Move to 'platform-be' and deploy on the same network
<br>https://github.com/kmponis/platform-application/blob/master/platform-be/README.md

## Deploy locally with docker 
* Move to 'platform-ui', Build and Deploy
<br>`$ docker build -t platformuiimage -f Dockerfile .`
<br>`$ docker run -p 4200:4200 platformuiimage`

## Test platform-ui
* Test platform_ui on your browser
<br>`$ open http://<docker_ip_address>:4200/`
> Note: To bypass login page you need to deploy platform-be using docker.
<br>Users:
<br>admin:admin, user:user
<br>Bugs:
<br>1) Login as 'user' -> Logout -> Wait for 30 seconds -> See 2 messages:
<br>'You have been automatically logged out'
<br>'You have successfully logged out'

## Upload to dockerhub for external use
* A dockerhub user is required
<br>`$ docker tag platformuiimage <your username>/platform-ui-image:latest`
<br>`$ docker login`
<br>`$ docker push <your username>/platform-ui-image:latest`

<br>
<br>
<br>
<br>
<br>

## General Info
#### Further Docker information
* Build image
<br>`> docker build -t <your username>/platform-ui .`
* View list of images
<br>`> docker images`
* Run image
<br>`> docker run -p 4300:4200 <your username>/platform-ui`
* Run image in background
<br>`> docker run -p 4300:4200 -d <your username>/platform-ui`
* Get container ID
<br>`> docker ps`
* Print app output
<br>`> docker logs <container id>`
* Enter the container
<br>`> docker exec -it <container id> /bin/bash`

#### Further Node information
* Build project: 
<br>`> npm install`
* Start application:
<br>**Localy NoteJS** 
<br>`> ng serve`
<br>If you don't have ng:
<br>`> npm run ng serve`
<br>**Localy Tomcat** 
<br>Build the project
<br>`> ng build --base-href /platform-ui/`
<br>If you don't have ng:
<br>`> npm run ng -- build --base-href /platform-ui/`
<br>Replace platform-ui on Tomcat webapp
<br>`> rmdir /s /q C:\jws-5.0\tomcat\webapps\platform-ui`
<br>`> xcopy dist\platform-ui C:\jws-5.0\tomcat\webapps\platform-ui`
<br>`> D`

###### Further Angular Information 
* Development server:
<br>This project was generated with [Angular CLI](https://github.com/angular/angular-cli) version 7.0.6.
* Code scaffolding:
<br>Run `ng serve` for a dev server. Navigate to `http://localhost:4200/`. The app will automatically reload if you change any of the source files.
* Build:
<br>Run `ng generate component component-name` to generate a new component. You can also use `ng generate directive|pipe|service|class|guard|interface|enum|module`.
* Running unit tests:
<br>Run `ng build` to build the project. The build artifacts will be stored in the `dist/` directory. Use the `--prod` flag for a production build.
* Running unit tests:
<br>Run `ng test` to execute the unit tests via [Karma](https://karma-runner.github.io).
* Running end-to-end tests:
<br>Run `ng e2e` to execute the end-to-end tests via [Protractor](http://www.protractortest.org/).
* Further help:
<br>To get more help on the Angular CLI use `ng help` or go check out the [Angular CLI README](https://github.com/angular/angular-cli/blob/master/README.md).