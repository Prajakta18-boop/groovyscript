pipeline{
    agent any 
   stages{ 
    stage('clone code') {
        steps{ 
            git branch: 'main', url: 'https://github.com/Prajakta18-boop/backendcode.git'
            }
            
        }
    }
}