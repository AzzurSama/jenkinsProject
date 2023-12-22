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
        stage('Delete old Docker image and Container') {
            steps {
                script {
                    bat 'docker images -q %jenkinsproject% > nul 2>&1'
                    bat 'if %errorlevel% neq 0 ('
                        bat "echo 'Image %jenkinsproject% n existe pas.'."
                    bat ')else('
                        bat 'docker rmi jenkinsproject'
                    bat ')'
                    bat 'docker inspect -f {{.State.Running}} %jenkinsproject% > nul 2>&1'
                    bat 'if %errorlevel% neq 0 ('
                        bat "echo 'Container %jenkinsproject% n existe pas.'."
                    bat ')else('
                        bat 'docker stop jenkinsproject'
                        bat 'docker rm jenkinsproject'
                    bat ')'
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
