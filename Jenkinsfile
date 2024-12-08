
pipeline {
    agent any

    stages {
        stage('Clone and Build project') {
            steps {
                git 'git@github.com:elistarkhov/todoapp.git'
                sh 'mvn clean package -DskipTests' 
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
                sh 'docker build -t elistarkhov/todoapp:v1 .'
            }
        }
        stage('Push Docker image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-login', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                sh "docker login -u ${env.dockerHubUser} --password-stdin ${env.dockerHubPassword}"
                sh 'docker push elistarkhov/todoapp:v1'
                }
            }
        }
    }
}

