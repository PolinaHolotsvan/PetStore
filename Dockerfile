FROM openjdk:18
COPY target/PetStore-0.0.1-SNAPSHOT.jar PetStore-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/PetStore-0.0.1-SNAPSHOT.jar"]
