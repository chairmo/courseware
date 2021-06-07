FROM maven:3.6.3-jdk-11 AS builder

#set current working directory inside the image
WORKDIR /workdir/server

#copy the pom.xml file
COPY pom.xml /workdir/server/pom.xml

#build the dependency in preparation to go offline
RUN mvn dependency:go-offline 

#copy the project src
COPY src /workdir/server/src

#package the application
RUN mvn install

RUN mkdir y-p target/dependency
WORKDIR /workdir/server/target/dependency
RUN jar -xf ../*.jar


#dockage image with command to run the app
FROM openjdk:11-jre-slim


#EXPOSE 9090
VOLUME /tmp

ARG DEPENDENCY=/workdir/server/target/dependency

#copy the project dependency from build stage
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib /app/lib
COPY --from=builder ${DEPENDENCY}/META-INF /app/META-INF
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes /app

ENTRYPOINT ["java","-cp","app:app/lib/*","com.chairmo.courseapp.CourseappApplication"]