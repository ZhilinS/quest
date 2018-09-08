FROM node:latest

WORKDIR /bindmount

COPY ./ ./

RUN npm install

CMD npm run build_start

EXPOSE 3000
