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
                sh 'docker-compose up -d'
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