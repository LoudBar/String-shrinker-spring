pipeline {
    agent any
    stages {
        stage("verifying") {
            steps {
                sh '''
                    docker version
                    docker-compose version
                    curl --version
                '''
            }
        }
    }
}