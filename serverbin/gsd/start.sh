#!/bin/sh
cd `dirname $0`
ulimit -c unlimited
WORKDIR=$PWD

mkdir -p xdb
mkdir -p xbackup

$WORKDIR/daemon -c $WORKDIR -f $WORKDIR/gsd.log -p $WORKDIR/gsd.pid java -Xms1g -Xmx24g -XX:+PrintGCDetails -Xloggc:gc.log -XX:+PrintGCTimeStamps -javaagent:classreloader.jar -jar gsd.jar gsd.config.my.xml
echo gsd started
