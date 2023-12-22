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
        stage('Arrêter et Supprimer le Conteneur Docker') {
            steps {
                script {
                    def containerName = 'jenkinsproject'

                    // Vérifier si le conteneur est en cours d'exécution
                    def containerRunning = sh(script: "docker inspect -f {{.State.Running}} ${containerName}", returnStatus: true) == 0

                    // Arrêter et supprimer le conteneur s'il est en cours d'exécution
                    if (containerRunning) {
                        sh "docker stop ${containerName}"
                        sh "docker rm ${containerName}"
                    } else {
                        echo "Le conteneur Docker ${containerName} n'est pas en cours d'exécution."
                    }
                }
            }
        }
        stage('Supprimer l\'image Docker') {
            steps {
                script {
                    def imageName = 'jenkinsproject'

                    // Vérifier si l'image existe
                    def imageExists = sh(script: "docker images -q ${imageName}", returnStatus: true) == 0

                    // Supprimer l'image si elle existe
                    if (imageExists) {
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
