pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9'
        jdk 'JDK-17'
    }
    
    environment {
        MAVEN_OPTS = '-Xmx1024m'
        APP_NAME = 'student-management'
        APP_VERSION = '1.0.0'
    }
    
    stages {
        stage('Checkout') {
            steps {
                echo 'Checkout nga Git...'
                checkout scm
            }
        }
        
        stage('Build') {
            steps {
                echo 'Duke ndërtuar projektin me Maven...'
                sh 'mvn clean install -DskipTests'
            }
        }
        
        stage('Unit Tests') {
            steps {
                echo 'Duke ekzekutuar unit tests...'
                sh 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Code Analysis') {
            steps {
                echo 'Duke analizuar kodin...'
                sh 'mvn verify -DskipTests'
            }
        }
        
        stage('Package') {
            steps {
                echo 'Duke krijuar JAR artifact...'
                sh 'mvn package -DskipTests'
            }
        }
        
        stage('Archive Artifacts') {
            steps {
                echo 'Duke arkivuar artifacts...'
                archiveArtifacts artifacts: '**/target/*.jar', fingerprint: true
            }
        }
        
        stage('Deploy to OpenShift') {
            steps {
                echo 'Duke deployuar në OpenShift...'
                script {
                    // Komanda për deploy në OpenShift
                    // sh 'oc login --token=<token> --server=<server>'
                    // sh 'oc project student-management'
                    // sh 'oc start-build student-management --from-file=./web/target/web-1.0.0.jar --follow'
                    echo 'OpenShift deployment konfigurohet në fazat e ardhshme'
                }
            }
        }
    }
    
    post {
        success {
            echo '✅ Pipeline përfundoi me sukses!'
            emailext(
                subject: "Jenkins Build SUCCESS: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "Build u krye me sukses.\n\nShiko detajet: ${env.BUILD_URL}",
                to: 'team@example.com'
            )
        }
        failure {
            echo '❌ Pipeline dështoi!'
            emailext(
                subject: "Jenkins Build FAILED: ${env.JOB_NAME} - ${env.BUILD_NUMBER}",
                body: "Build dështoi.\n\nShiko detajet: ${env.BUILD_URL}",
                to: 'team@example.com'
            )
        }
        always {
            echo 'Pastrimi i workspace...'
            cleanWs()
        }
    }
}
