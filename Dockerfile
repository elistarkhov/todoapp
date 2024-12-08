FROM tomcat:10.1-jdk17

COPY target/ROOT.war /usr/local/tomcat/webapps/