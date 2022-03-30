FROM openjdk:18
ADD target/blog-api-docker.jar blog-api-docker.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","blog-api-docker.jar"]