ARG MCSV_NAME=mcsv-auth

FROM openjdk:17-jdk-alpine as builder

ARG MCSV_NAME

WORKDIR /app/$MCSV_NAME

COPY ./pom.xml /app
COPY ./$MCSV_NAME/.mvn ./.mvn
COPY ./$MCSV_NAME/mvnw .
COPY ./$MCSV_NAME/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/

COPY ./$MCSV_NAME/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

ARG MCSV_NAME

WORKDIR /app
RUN mkdir ./logs

ARG TARGET_FOLDER=/app/$MCSV_NAME/target
COPY --from=builder $TARGET_FOLDER/mcsv-auth-0.0.1-SNAPSHOT.jar .
ARG PORT_APP=9000
ENV PORT $PORT_APP
EXPOSE $PORT

CMD ["java","-jar","mcsv-auth-0.0.1-SNAPSHOT.jar"]
