#!/bin/sh
cd `dirname $0`

kill `cat xauany.pid`	
sleep 5
rm -f xauany.pid

echo  xauany stopped
