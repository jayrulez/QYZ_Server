#!/bin/sh
cd `dirname $0`

if [ -f serviced.pid  ]; then
	PID=`cat serviced.pid`
	ps -o pid,%cpu,rss,args --no-heading --pid $PID
    netstat -natp | grep $PID
else
    echo "serviced not running"
fi
exit 0
