#!/bin/sh
cd `dirname $0`

if [ -f xlink1.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink1.pid` 2>/dev/null
        netstat -natp | grep `cat xlink1.pid`
else
        echo "xlink1 not running"
fi
if [ -f xlink2.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink2.pid` 2>/dev/null
        netstat -natp | grep `cat xlink2.pid`
else
        echo "xlink2 not running"
fi
if [ -f xlink3.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink3.pid` 2>/dev/null
        netstat -natp | grep `cat xlink3.pid`
else
        echo "xlink3 not running"
fi
if [ -f xlink4.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink4.pid` 2>/dev/null
        netstat -natp | grep `cat xlink4.pid`
else
        echo "xlink4 not running"
fi
if [ -f xlink5.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink5.pid` 2>/dev/null
        netstat -natp | grep `cat xlink5.pid`
else
        echo "xlink5 not running"
fi
if [ -f xlink6.pid  ]; then
        ps -o pid,%cpu,rss,args --no-heading --pid `cat xlink6.pid` 2>/dev/null
        netstat -natp | grep `cat xlink6.pid`
else
        echo "xlink6 not running"
fi
exit 0
