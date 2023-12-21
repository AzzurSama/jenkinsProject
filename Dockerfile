FROM rsunix/yourkit-openjdk21

ADD target/jenkinsProject.jar jenkinsProject.jar
ENTRYPOINT ["java", "-jar", "jenkinsProject.jar"]
EXPOSE 8080