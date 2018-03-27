FROM maven:3.5.3-jdk-8-alpine

EXPOSE 8080

COPY . /app
WORKDIR /app

RUN mvn install

RUN mvn test

RUN mvn package

CMD java -jar target/JavaServer.jar