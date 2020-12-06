#父镜像，Java环境一般就是Java8
FROM java:8
#镜像发布人信息，可省略
MAINTAINER pang 'starry-sky-zhang@foxmail.com'
#定义匿名数据卷
VOLUME /tmp
#容器正式启动前执行的指令，此处是更正时区
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
&& echo 'Asia/Shanghai' >/etc/timezone
#将宿主机文件复制到容器中
COPY target/iotat-api-gateway-starter-0.0.1.SNAPSHOT.jar app.jar
#docker启动时候执行的指令，执行到这里证明docker容器已经启动
ENTRYPOINT ["java","-jar","/app.jar","--spring.profiles.active=${profile}"]
