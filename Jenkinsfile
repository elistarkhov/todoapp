
def TODOAPP_VERSION
def APP_IMAGE = 'elistarkhov/todoapp'
def CD_REPO = 'git@github.com:elistarkhov/todoapp-argocd.git'

pipeline {
    agent any

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
                sh "docker build -t ${APP_IMAGE}:${TODOAPP_VERSION} ."
            }
        }
        stage('Push Docker image') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'docker-login', passwordVariable: 'dockerHubPassword', usernameVariable: 'dockerHubUser')]) {
                sh "docker login -u ${env.dockerHubUser} -p ${env.dockerHubPassword}"
                sh "docker push ${APP_IMAGE}:${TODOAPP_VERSION}"
                }
            }
        }

        stage('Clear Docker Local Registry') {
            steps {
                sh "docker rmi -f ${APP_IMAGE}:${TODOAPP_VERSION}"
            }
        }

        stage('Update CD Repository') {
            steps {                
                script {
                    git branch: 'main',
                        credentialsId: 'github-deploy-key',
                        url: "${CD_REPO}"

                    def values = readYaml file: 'HelmCharts/MyChart/values.yaml'
                    values.container.image = "${APP_IMAGE}:${TODOAPP_VERSION}-TEST3"
                    writeYaml file: 'HelmCharts/MyChart/values.yaml', data: values, overwrite: true
                }
                sshagent (credentials: ['github-deploy-key']) {
                    sh """
                        git add HelmCharts/MyChart/values.yaml
                        git commit -m "[Jenkins] Updated docker image tag - ${TODOAPP_VERSION}"
                        git push "${CD_REPO}" main
                    """
                }
            }
        }
    }
}


