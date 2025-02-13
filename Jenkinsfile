pipeline {
    agent {
        docker { image 'docker:latest' }
    }

    environment {
        DOCKER_IMAGE = 'abiraj165/bootstrap-app:latest'
        K8S_DEPLOYMENT = 'k8s/bootstrap-deployment.yaml'
    }

    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/abiraj-abiraj123/bootstrap-on-shoes.git'
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    def app = docker.build("${DOCKER_IMAGE}")
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    docker.withRegistry('', 'dockerhub-credentials') {
                        docker.image("${DOCKER_IMAGE}").push()
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    sh 'kubectl apply -f $K8S_DEPLOYMENT'
                }
            }
        }
    }
}
