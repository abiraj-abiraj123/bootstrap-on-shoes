pipeline {
    agent any
    environment {
        DOCKER_IMAGE = 'abiraj165/bootstrap-app/Dockerfile'
        K8S_DEPLOYMENT = 'k8s/bootstrap-deployment.yaml'
    }
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', credentialsId: 'github-credentials', url: 'https://github.com/abiraj-abiraj123/bootstrap-on-shoes.git'
            }
        }

        stage('Build and Push Image with Kaniko') {
            steps {
                container('kaniko') {
                    sh '''
                    /kaniko/executor --context `pwd` --dockerfile `pwd`/Dockerfile \
                    --destination=abiraj165/bootstrap-app:latest \
                    --cache=true
                    '''
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
