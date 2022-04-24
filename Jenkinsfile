pipeline {
    agent any
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
        stage('Clear all docker data') {
            steps {
                sh 'docker stop application'
                sh 'docker rm application'
                sh 'docker image rm application-image'
                sh 'docker stop redis'
                sh 'docker rm redis'
                sh 'docker image rm redis-image'

            }
        }

        stage('Build and start docker containers') {
            steps {
                sh 'docker-compose up'
                sh 'docker-compose ps'
            }
        }
    }
    post {
        always {
            sh 'docker-compose down'
            sh 'docker-compose ps'
        }
    }
}