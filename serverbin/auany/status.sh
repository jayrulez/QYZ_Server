#!/bin/sh
cd `dirname $0`

if [ -f xauany.pid  ]; then
	PID=`cat xauany.pid`
	ps -o pid,%cpu,rss,args --no-heading --pid $PID
    netstat -natp | grep $PID
else
    echo "xauany not running"
fi
exit 0
