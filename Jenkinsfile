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
       stage('Stop and Remove Docker Container') {
           steps {
               script {
                   def containerName = 'nostalgic_wu'
                   if (bat(script: "docker ps --filter \"name=$containerName\" | findstr $containerName", returnStatus: true) == 0) {
                       echo "Le conteneur Docker $containerName existe et est en cours d'exécution."
                   } else {
                       echo "Le conteneur Docker $containerName n'existe pas ou n'est pas en cours d'exécution."
                   }
               }
           }
       }

        stage('Supprimer l\'image Docker') {
            steps {
                script {
                    def imageName = 'jenkinsproject'

                    // Vérifier si l'image existe
                    //def imageExists = sh(script: "docker images -q ${imageName}", returnStatus: true) == 0

                    // Supprimer l'image si elle existe
                    if (true) {
                        sh "docker rmi ${imageName}"
                    } else {
                        echo "L'image Docker ${imageName} n'existe pas."
                    }
                }
            }
        }
        stage('Build Docker image') {
            steps {
                script {
                    dockerImage = docker.build('jenkinsproject:latest')
                }
            }
        }
        stage('Start Docker Container') {
            steps {
                bat 'docker run --name jenkinsproject -d -p 9075:8080 jenkinsproject:latest projectJenkins.jar'
            }
        }
    }
}
