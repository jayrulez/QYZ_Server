#!/bin/sh
cd `dirname $0`

# 对pid中记录deliver进程进行检查
if [ -f xdeliver.pid  ]; then
    ps -o pid,%cpu,rss,args --no-heading --pid `cat xdeliver.pid` 2>/dev/null
    netstat -natp | grep `cat xdeliver.pid`
else
    echo "xdeliver not running"
fi 
exit 0
