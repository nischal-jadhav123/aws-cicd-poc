FROM openjdk:17
EXPOSE 8080
ADD target/springboot-docker-ecs.jar springboot-docker-ecs.jar
ENTRYPOINT ["java","-jar","/springboot-docker-ecs.jar"]