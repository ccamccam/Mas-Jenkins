pipeline {
  agent any

  stages {

    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Build + Test + Coverage + Jar') {
      steps {
        sh 'mvn -B clean verify'
      }
    }

    stage('Static Analysis (SonarQube)') {
      steps {
        withSonarQubeEnv('sonarqube') {
          sh '''
            mvn -B sonar:sonar \
              -Dsonar.projectKey=mas-jenkins \
              -Dsonar.host.url=http://localhost:9000 \
              -Dsonar.login=$SONAR_TOKEN
          '''
        }
      }
    }
  }

  post {

    always {
      // Publish JUnit test results
      junit 'target/surefire-reports/*.xml'

      // Archive JAR artifact
      archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: false

      // Archive JaCoCo coverage report
      archiveArtifacts artifacts: 'target/site/jacoco/**', allowEmptyArchive: true
    }

    success {
      emailext(
        to: 'chris@local.test',
        subject: "SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: "Build succeeded.\n\n${env.BUILD_URL}"
      )
    }

    failure {
      emailext(
        to: 'chris@local.test',
        subject: "FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
        body: "Build failed.\n\n${env.BUILD_URL}"
      )
    }
  }
}
