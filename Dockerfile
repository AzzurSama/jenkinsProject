FROM rsunix/yourkit-openjdk17

ADD target/jenkinsProject.jar jenkinsProject.jar
ENTRYPOINT ["java", "-jar", "jenkinsProject.jar"]
EXPOSE 8080