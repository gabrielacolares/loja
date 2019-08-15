pipeline {
    agent any
    stages {
        stages {
            stage('Build') {
                steps {
                    sh 'echo "build"'
                    sh ' mvn spring-boot:clean install'
                }
            }
        stage('Stage2') {
            steps {
                sh 'echo "Hello World"'
                sh '''
                    echo "Multiline shell steps works too"
                    ls -lah
                '''
            }
        }

    }
}