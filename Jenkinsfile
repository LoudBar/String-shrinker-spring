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
                sh 'docker system prune -a --volumes -f'
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