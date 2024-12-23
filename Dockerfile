FROM tomcat:10.1-jdk17

ENV CATALINA_OPTS="-Xmx512m"

COPY target/ROOT.war /usr/local/tomcat/webapps/