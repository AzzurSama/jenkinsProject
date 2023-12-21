FROM rsunix/yourkit-openjdk17

ADD target/projectJenkins.jar projectJenkins.jar
ENTRYPOINT ["java", "-jar", "projectJenkins.jar"]
EXPOSE 8080