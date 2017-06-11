FROM tomcat:8.5-alpine
VOLUME /tmp
ADD /home/veladii/IdeaProjects/ParkIt/build/docker/myname.war app.war

COPY app.war /usr/local/tomcat/webapps/app.war
RUN sh -c 'touch /usr/local/tomcat/webapps/app.war'

ENTRYPOINT [ "sh", "-c", "java -Djava.security.egd=file:/dev/./urandom -war /usr/local/tomcat/webapps/app.war" ]
