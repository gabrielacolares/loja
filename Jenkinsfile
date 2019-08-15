pipeline {
    agent any
        stages {
            stage('Build') {
                steps {
                    sh 'echo "build"'
                    sh 'cd /gs-gradle'
                    sh ' run.sh'
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