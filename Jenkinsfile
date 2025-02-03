pipeline {
    agent any  // Runs on any available Jenkins node

    environment {
        PARALLEL_EXECUTION = "true"  // Enable parallel execution
        THREAD_COUNT = "2"  // Define number of parallel threads
        BROWSER = "chrome"  // Default browser for execution
    }

    stages {
        stage('Checkout Code') {
            steps {
                git branch: 'main', url: 'https://github.com/your-repo.git'  // Replace with your repo
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test -Dcucumber.execution.parallel.enabled=$PARALLEL_EXECUTION -Dparallel.thread.count=$THREAD_COUNT -Dbrowser=$BROWSER'
            }
        }

        stage('Generate Reports') {
            steps {
                sh 'mvn surefire-report:report'  // Generates Surefire test reports
            }
        }

        stage('Publish Reports') {
            steps {
                publishHTML(target: [
                    allowMissing: false,
                    alwaysLinkToLastBuild: true,
                    keepAll: true,
                    reportDir: 'target/cucumber-reports',
                    reportFiles: 'Cucumber.html',
                    reportName: 'Cucumber Test Report'
                ])
            }
        }
    }
}
