# Docker Info
## Deploy on localhost
* Enter 'y' when deploying first time
<br>`$ docker-compose up --build`

## Docker Debug
* Make sure all container are available to one another
<br>`$ docker-compose exec platform_ui /bin/sh`
<br>`$ ping platform_be`
<br>`$ docker-compose exec platform_be /bin/sh`
<br>`$ ping platform_ui`
