pipeline{
    agent any 
   stages{ 
    stage('clone code') {
        steps{ 
            git branch: 'main', url: 'https://github.com/Prajakta18-boop/newgroovy.git'
            }
            
    }
    stage('deploying code') {
        steps{
            sh 'mv /var/lib/jenkins/workspace/agentA/index.html /var/www/html/jewels'
        }
    }   
    stage ('restarting nginx'){
        steps{
           sh 'sudo systemctl restart nginx'
        }
    }
   }

}