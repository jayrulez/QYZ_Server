del xdb\xdb.inuse
del xdb\trace.log
java -ea -d64 -server -javaagent:../lib/classreloader.jar -XX:+PrintGCDetails -Xloggc:gc.log -XX:+PrintGCTimeStamps -Xms1g -Xmx2g -Xrunjdwp:transport=dt_socket,address=8448,server=y,suspend=n -jar gsd.jar gsd.config.my.xml

pause