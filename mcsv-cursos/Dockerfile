FROM openjdk:17-jdk-alpine as builder

WORKDIR /app/mcsv-cursos

COPY ./pom.xml /app
COPY ./mcsv-cursos/.mvn ./.mvn
COPY ./mcsv-cursos/mvnw .
COPY ./mcsv-cursos/pom.xml .

#RUN ./mvnw clean package -Dmaven.test.skip -Dmaven.main.skip -Dspring-boot.repackage.skip && rm -r ./target/
RUN ./mvnw dependency:go-offline #Otra forma

COPY ./mcsv-cursos/src ./src

RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app
RUN mkdir ./logs

COPY --from=builder /app/mcsv-cursos/target/mcsv-cursos-0.0.1-SNAPSHOT.jar .

EXPOSE 8002

#ENTRYPOINT ["java","-jar","mcsv-cursos-0.0.1-SNAPSHOT.jar"]
CMD ["java","-jar","mcsv-cursos-0.0.1-SNAPSHOT.jar"]
