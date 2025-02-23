FROM openjdk:17
ADD  target/assignment.jar assignment.jar
ENTRYPOINT ["java", "-jar", "assignment.jar"]