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
                    def containerName = 'jenkinsproject'

                    // Arrêter et supprimer le conteneur s'il est en cours d'exécution
                    try {
                        // Vérifier si le conteneur est en cours d'exécution
                        def containerRunning = bat(script: "docker ps -q --filter name=$containerName", returnStatus: true) == 0

                        if (containerRunning) {
                            script {
                                // Utiliser le plugin Durable Task pour exécuter les commandes Docker
                                def stopProcess = bat "docker stop $containerName", returnStatus: true
                                if (stopProcess != 0) {
                                    echo "Le conteneur Docker $containerName n'est pas en cours d'exécution."
                                } else {
                                    echo "Le conteneur Docker $containerName a été arrêté avec succès."
                                }

                                // Supprimer le conteneur
                                def removeProcess = bat "docker rm $containerName", returnStatus: true
                                if (removeProcess != 0) {
                                    echo "Échec de la suppression du conteneur Docker $containerName."
                                } else {
                                    echo "Le conteneur Docker $containerName a été supprimé avec succès."
                                }
                            }
                        } else {
                            echo "Le conteneur Docker $containerName n'est pas en cours d'exécution."
                        }
                    } catch (Exception e) {
                        echo "Erreur lors de la vérification du conteneur : ${e.message}"
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
