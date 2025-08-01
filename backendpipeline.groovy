pipeline {
    agent any
    tools {
        maven 'maven3.9.8'
    }

    stages {
        stage('Clone Code') {
            steps {
                git branch: 'main', url: 'https://github.com/Prajakta18-boop/backendcode.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Send Artifact to Tomcat Server') {
            steps {
                sshagent(['jenkinsserver']) {
                    sh 'scp -o StrictHostKeyChecking=no target/*.war azureuser@20.193.142.223:/home/azureuser/'
                }
            }
        }

        stage('Deploy Artifact') {
            steps {
                sshagent(['tomcat-server']) {
                    sh 'ssh -o StrictHostKeyChecking=no azureuser@4.186.26.126 "sudo mv /home/azureuser/*.war /opt/tomcat/webapps/"'
                }
            }
        }
    }
}

   
