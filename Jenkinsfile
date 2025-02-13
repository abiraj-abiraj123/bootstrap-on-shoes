pipeline {
    agent any

    environment {
        DOCKER_IMAGE = 'abiraj165/bootstrap-app/Dockerfile'
        K8S_DEPLOYMENT = 'k8s/bootstrap-deployment.yaml'
    }

    stages {
        stage('Clone Repository') {
            steps {
                script {
                    echo "Cloning repository..."
                    git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/abiraj-abiraj123/bootstrap-on-shoes.git'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    echo "Building Docker image..."
                    sh 'docker build -t $DOCKER_IMAGE .'
                }
            }
        }

        stage('Push Image to Docker Hub') {
            steps {
                script {
                    echo "Pushing Docker image..."
                    withDockerRegistry([credentialsId: 'dockerhub-credentials', url: '']) {
                        sh 'docker push $DOCKER_IMAGE'
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                script {
                    echo "Deploying to Kubernetes..."
                    sh 'kubectl apply -f $K8S_DEPLOYMENT'
                }
            }
        }
    }
}
