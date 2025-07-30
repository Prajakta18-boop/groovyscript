pipeline{
    agent any 
    tools {
        maven 'maven3.9.8'
    }
   
   stages{ 

    stage('clone code') {
        steps{ 
            git branch: 'main', url: 'https://github.com/Prajakta18-boop/backendcode.git'
            }
            
        }
        stage('build'){
            steps{
                sh 'mvn clean package'
            }

        }
        stage('sending artifact to tomcat server'){
            steps{
                sh 'scp target/*.war azureuser@4.186.26.126:/home/azureuser/'
            }

        }
        stage('deploying artifact'){
            steps{
                sshagent(['tomcat server']){
                    sh 'ssh -o StrictHostKeyChecking=no azureuser@4.186.26.126 "sudo mv /home/azureuser/*.war /opt/tomcat/webapps"'
                }
            }
        }
    }
    
}