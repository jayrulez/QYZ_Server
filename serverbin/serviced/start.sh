#!/bin/sh
cd `dirname $0`
ulimit -c unlimited
WORKDIR=$PWD

$WORKDIR/daemon -c $WORKDIR -f $WORKDIR/serviced.log -p $WORKDIR/serviced.pid java -XX:+PrintGCDetails -Xloggc:gc.log -XX:+PrintGCTimeStamps -Xms1g -Xmx8g -javaagent:classreloader.jar -jar serviced.jar serviced.config.my.xml
echo serviced started
