### STAGE 1: Build ###
FROM node:11 AS build
WORKDIR /usr/src/app
COPY package.json ./
RUN npm install
COPY . .
# Run Angular and save static content to /dist/out
RUN npm run build -- --output-path=./dist/out

### STAGE 2: Deploy ###
FROM nginx:1.17.1-alpine
# Added for debug options, hit '$ docker-compose exec platform_ui /bin/sh' and curl endpoints
RUN apk add --update curl
# Copy Angular static content to nginx
COPY --from=build /usr/src/app/dist/out /usr/share/nginx/html
# Update nginx default.conf with platform_ui.conf
RUN rm /etc/nginx/conf.d/default.conf
COPY ./nginx/conf.d/platform_ui.conf /etc/nginx/conf.d/default.conf
EXPOSE 80
CMD ["nginx", "-g", "daemon off;"]