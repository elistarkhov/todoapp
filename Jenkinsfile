
def TODOAPP_VERSION

pipeline {
    agent any
    triggers { pollSCM('* * * * *') }
    stages {
        stage('Build project') {
            steps {
                sh 'mvn clean package -DskipTests' 
                script {
                    TODOAPP_VERSION = sh script: 'mvn help:evaluate -Dexpression=project.version -q -DforceStdout', returnStdout: true  
                }
            }
        }

        stage('Run Tests') {
            steps {
                sh 'mvn test'
            }
            post {
                success {
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
                failure {
                    echo '===== TESTS FAILED ====='
                    junit '**/target/surefire-reports/TEST-*.xml'
                }
            }
        }

        stage('Build Docker image') {
            steps {
                sh "docker build -t elistarkhov/todoapp:${TODOAPP_VERSION} ."
            }
        }
        stage('Push Docker image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-login', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                sh "docker push elistarkhov/todoapp:${TODOAPP_VERSION}"
                }
            }
        }

        stage('Clear docker local registry') {
            steps {
                sh "docker rmi -f elistarkhov/todoapp:${TODOAPP_VERSION}"
            }
        }
    }
}


