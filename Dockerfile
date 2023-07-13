FROM openjdk:11
EXPOSE 8080
ADD target/cbm.jar cbm.jar
ENTRYPOINT ["java","-jar","/cbm.jar"]
