pipeline {
    agent any

    environment {
        DOCKER_HUB_CREDENTIALS = credentials('loudbar-dockerhub')
    }
    stages {
        stage('verifying') {
            steps {
                sh '''
                    docker version
                    docker-compose version
                    curl --version
                '''
            }
        }
        stage('Running tests') {
            steps {
                sh './mvnw test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }
        stage('Build and start docker containers') {
            steps {
                sh 'docker-compose down'
                sh 'docker-compose up -d'            }
        }
        stage('Login into Docker hub') {
            steps {
                sh 'echo $DOCKER_HUB_CREDENTIALS_PSW | docker login -u $DOCKER_HUB_CREDENTIALS_USR --password-stdin'
            }
        }
        stage('Push image to Docker hub') {
            steps {
                sh 'docker push loudbar/string-shrink:latest'
                sh 'docker push loudbar/string-shrink_db:latest'
            }
        }
        stage('EKS Deploy') {
            kubernetesDeploy (
                configs: 'String-shrinker-spring/app-k8s.yml',
                kubeconfigId: 'k8s-aws',
                enableConfigSubstitution: true
            )
        }
    }
    post {
        always {
            sh 'docker logout'
            sh 'docker-compose down'
            sh 'docker-compose ps'
        }
    }
}