pipeline {
      agent any
      stages {
         stage ('Build') {
          steps {
             sh '''cd $WORKSPACE
                   docker build -t devops:v${BUILD_NUMBER} .'''
             }
           }
           stage('push ECR'){
            steps {
                sh '''aws ecr get-login-password --region us-east-1 | docker login --username AWS --password-stdin 931189440263.dkr.ecr.us-east-1.amazonaws.com
           		    
	
					docker tag devops:v${BUILD_NUMBER} 931189440263.dkr.ecr.us-east-1.amazonaws.com/container:v${BUILD_NUMBER}

		   docker push 931189440263.dkr.ecr.us-east-1.amazonaws.com/container:v${BUILD_NUMBER}
       '''
          }
          }
      }
}
