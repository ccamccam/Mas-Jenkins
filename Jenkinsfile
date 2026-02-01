pipeline {
  agent any

  stages {
    stage('Checkout') {
      steps { checkout scm }
    }

    stage('Build + Test + Coverage + Jar') {
      steps {
        sh 'mvn -B clean verify'
      }
    }

    // --- Static Analysis (SonarQube) ---
    // If you set up SonarQube in Jenkins global config:
    // stage('Static Analysis (SonarQube)') {
    //   steps {
    //     withSonarQubeEnv('sonarqube') {
    //       sh 'mvn -B sonar:sonar'
    //     }
    //   }
    // }
  }

  post {
    always {
      // Test results
      junit 'target/surefire-reports/*.xml'

      // Keep artifacts + reports
      archiveArtifacts artifacts: 'target/*.jar', allowEmptyArchive: false
      archiveArtifacts artifacts: 'target/site/jacoco/**', allowEmptyArchive: true
    }

    success {
      echo 'Build SUCCESS'
      // Optional email notification (requires SMTP configured in Jenkins):
      // mail to: 'you@example.com',
      //      subject: "Jenkins SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
      //      body: "Build succeeded: ${env.BUILD_URL}"
    }

    failure {
      echo 'Build FAILURE'
      // mail to: 'you@example.com',
      //      subject: "Jenkins FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
      //      body: "Build failed: ${env.BUILD_URL}"
    }
  }
}
