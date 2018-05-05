#!/bin/sh
cd `dirname $0`
kill `cat serviced.pid`
rm -f serviced.pid

echo serviced stopped
