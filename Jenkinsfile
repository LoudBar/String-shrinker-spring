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
        stage('Start') {
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