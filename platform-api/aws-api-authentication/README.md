# AWS-API-AUTHENTICATION
An AWS serverless Kotlin project to authenticate User

>Endpoints
<br> - GET /, no params
<br> - GET /encrypt, params(text=123)
<br> - POST /addUser, params(username=admin&password=admin&userRole=ADMIN)
<br> - GET /getAllUsers, no params
<br> - POST /authentication, params(username=admin&password=admin)
<br> - OPTION /authentication, no params

## Deploy Locally 
* Build Locally, go to the root 
<br>`mvn install`
* Deploy Locally, got to local-server
<br>`mvn exec:exec`
* Test on localhost
<br>`open http://localhost:8080/`

## Deploy on AWS
* Build and Deploy on AWS 
<br>`mvn deploy`

### Osiris plugin 
* The project was created using the bellow command.
<br>`$ mvn archetype:generate -DarchetypeGroupId=ws.osiris -DarchetypeArtifactId=osiris-archetype -DarchetypeVersion=1.4.0`
* The bellow prerequisites were needed:
<br>- JDK8, Maven and Git
<br>- An AWS account.
<br>- An AWS user with administrator permissions, access key and secret access key.
<br>- Added access key and secret access key to system environments. Add following lines to .bash_profile.
<br>`export AWS_ACCESS_KEY_ID="<access_key>"`
<br>`export AWS_SECRET_ACCESS_KEY="<secret_access_key>"`
<br>`export AWS_REGION="<region>"`

##### Git examples
* Osiris
 <br>https://github.com/cjkent/osiris/wiki/Getting-Started-with-Maven
 <br>https://github.com/cjkent/osiris-examples/tree/master/dynamodb
* DynamoDB mapper
 <br>https://gist.github.com/gaplo917/a4298d755c076b1a295026ed9b3521fa
* Encryption 
 <br>https://github.com/SergiiShapoval/AWS-KMS-encryption-example
 <br>https://github.com/rrohaill/Cryptography
* Jwt-Demo
 <br>https://github.com/jwtk/jjwt
 <br>https://github.com/scottbrady91/Kotlin-Jwt-Demo
 <br>https://github.com/auth0/java-jwt