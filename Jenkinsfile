pipeline {
    agent any

    tools {
        maven 'Maven_3.9.6'
    }

    stages {
        stage('Hello'){
            steps {
                script {
                    echo 'Hello World !'
                }
            }
        }
        stage('Maven Build'){
            steps {
                bat 'mvn clean package'
            }
            post {
                success {
                    archiveArtifacts 'target/*.jar'
                }
            }
        }
       stage('Build Docker Image') {
           steps {
               script {
                   dockerImage = docker.build('jenkinsproject:latest')
                   bat "docker image prune -f"
              }
           }
       }

       stage('Start Docker Container') {
           steps {
               script {
                   try {
                       bat "docker stop jenkinsproject"
                       bat "docker rm jenkinsproject"
                   } catch (Exception e) {
                      echo '404 Not Found : jenkinsproject'
                   }
                   bat "docker run --name jenkinsproject -d -p 9075:8080 jenkinsproject:latest projectJenkins.jar"
               }
           }
       }
}