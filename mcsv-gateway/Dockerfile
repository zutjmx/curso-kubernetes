FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/mcsv-gateway

COPY ./pom.xml /app
COPY ./mcsv-gateway/.mvn ./.mvn
COPY ./mcsv-gateway/mvnw .
COPY ./mcsv-gateway/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./mcsv-gateway/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/mcsv-gateway/target/mcsv-gateway-0.0.1-SNAPSHOT.jar .

EXPOSE 8090

CMD ["java","-jar","mcsv-gateway-0.0.1-SNAPSHOT.jar"]
