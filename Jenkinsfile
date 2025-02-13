pipeline {
    agent any  // Run on any available Jenkins agent

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
                docker.build('$DOCKER_IMAGE .')
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                    sh 'docker push $DOCKER_IMAGE'
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                sh 'kubectl apply -f $K8S_DEPLOYMENT'
            }
        }
    }
}
