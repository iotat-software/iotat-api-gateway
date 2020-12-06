/**
 * pipeline文件，详细使用查看文档
 **/
def DOCKER_NAME = "iotat-api-gateway"
def IMAGE_NAME  = "iotat/iotat-api-gateway"
def GITHUB_URL = "https://github.com/iotat-software/otat-api-gateway.git"
def PORT = "8080:8080"
pipeline {
    agent any
    stages {
        stage('停止正在运行的容器') {
            steps {
                echo "停止正在运行的容器..."
                sh "docker ps -f name=${DOCKER_NAME} -q | xargs --no-run-if-empty docker container stop"
                sh "docker ps -a -f name=${DOCKER_NAME} -q | xargs --no-run-if-empty docker container rm"
            }
        }
        stage('拉取代码') {
            steps {
                echo '拉取代码...'
                // Get some code from a GitHub repository
                git url: "${GITHUB_URL}", branch: 'main'

                // To run Maven on a Windows agent, use
                // bat "mvn -Dmaven.test.failure.ignore=true clean package"
            }
        }
        stage('构建jar包') {
            steps {
                echo "开始构建jar包..."
                // Run Maven on a Unix agent.
                sh "mvn -Dmaven.test.skip=true clean package"
            }
        }
        stage('打包Docker镜像') {
            steps {
                echo "打包Docker镜像..."
                sh "cd ${DOCKER_NAME}-starter/src/main/Docker && docker build -t ${IMAGE_NAME} -f ./Dockerfile ../../../"
            }
        }
        stage('启动Docker镜像') {
            steps {
                echo "docker image start..."
                sh "docker run -d -p ${PORT} -v /home/iotat/logs:/logs/ --add-host=iotat.cn:172.17.0.1 --restart=always --name ${DOCKER_NAME} ${IMAGE_NAME}"
            }
        }
    }
}
