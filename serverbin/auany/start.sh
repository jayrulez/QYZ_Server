#!/bin/sh
cd `dirname $0`

ulimit -c unlimited
WORKDIR=$PWD

mkdir -p $WORKDIR/xdb
mkdir -p $WORKDIR/xbackup

$WORKDIR/daemon  -f $WORKDIR/xauany.log -c $WORKDIR -p $WORKDIR/xauany.pid java -javaagent:classreloader.jar -Xms1g -Xmx54g -jar xauany.jar xauany.config.my.xml

echo  xauany started!
