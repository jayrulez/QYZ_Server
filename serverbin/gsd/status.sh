#!/bin/sh
cd `dirname $0`

if [ -f gsd.pid  ]; then
	PID=`cat gsd.pid`
	ps -o pid,%cpu,rss,args --no-heading --pid $PID
    netstat -natp | grep $PID
else
    echo "gsd not running"
fi	
exit 0
