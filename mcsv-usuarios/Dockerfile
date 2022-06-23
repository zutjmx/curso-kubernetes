FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/mcsv-usuarios

COPY ./pom.xml /app
COPY ./mcsv-usuarios/.mvn ./.mvn
COPY ./mcsv-usuarios/mvnw .
COPY ./mcsv-usuarios/pom.xml .

RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
#RUN ./mvnw dependency:go-offline #Otra forma

COPY ./mcsv-usuarios/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app

COPY --from=builder /app/mcsv-usuarios/target/mcsv-usuarios-0.0.1-SNAPSHOT.jar .

EXPOSE 8001

ENTRYPOINT ["java","-jar","mcsv-usuarios-0.0.1-SNAPSHOT.jar"]