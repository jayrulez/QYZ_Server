#!/bin/sh
cd `dirname $0`

if [ -f xdeliver.pid ];then
	kill `cat xdeliver.pid`	2>/dev/null
fi

rm -f xdeliver.pid			2>/dev/null
echo  xdeliver stopped
