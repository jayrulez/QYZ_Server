#!/bin/sh
cd `dirname $0`
kill `cat gsd.pid`
sleep 10
rm -f gsd.pid

echo gsd stopped
