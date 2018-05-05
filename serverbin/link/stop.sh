#!/bin/sh
cd `dirname $0`
if [ -f xlink1.pid  ]; then
    kill `cat xlink1.pid`
fi
rm -f xlink1.pid                       2>/dev/null

if [ -f xlink2.pid  ]; then
    kill `cat xlink2.pid`
fi
rm -f xlink2.pid                       2>/dev/null

if [ -f xlink3.pid  ]; then
    kill `cat xlink3.pid`
fi
rm -f xlink3.pid                       2>/dev/null

if [ -f xlink4.pid  ]; then
    kill `cat xlink4.pid`
fi
rm -f xlink4.pid                       2>/dev/null

if [ -f xlink5.pid  ]; then
    kill `cat xlink5.pid`
fi
rm -f xlink5.pid                       2>/dev/null

if [ -f xlink6.pid  ]; then
    kill `cat xlink6.pid`
fi
rm -f xlink6.pid                       2>/dev/null

echo  xlink stopped
