pipeline {
    agent any
        stages {
            stage('Build') {
                steps {
                    sh 'echo "build"'
                    sh ' mvn spring-boot:clean install'
                }
            }
            stage('Test') {
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