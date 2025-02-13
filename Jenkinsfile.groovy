pipeline {
    agent {
    label 'jenkins-agent'
}

            yaml """
apiVersion: v1
kind: Pod
metadata:
  labels:
    some-label: kaniko
spec:
  containers:
    - name: kaniko
      image: gcr.io/kaniko-project/executor:latest
      args: ["--dockerfile=Dockerfile", "--context=dir:///workspace/", "--destination=abiraj165/bootstrap-app:latest"]
      volumeMounts:
        - name: kaniko-secret
          mountPath: /kaniko/.docker
  volumes:
    - name: kaniko-secret
      secret:
        secretName: docker-registry-secret
"""
        }
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

        stage('Build and Push Docker Image') {
            steps {
                container('kaniko') {
                    sh '/kaniko/executor --dockerfile=Dockerfile --context=dir:///workspace/ --destination=$DOCKER_IMAGE'
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
