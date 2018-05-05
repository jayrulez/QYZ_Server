del trace.log
java -ea -d64 -server -javaagent:../lib/classreloader.jar -Xdebug -Xrunjdwp:transport=dt_socket,address=8447,server=y,suspend=n -jar serviced.jar serviced.config.xml

pause