#!/bin/sh
roleid=$1
level=$2
passwd="tswcbyy"
echo "Usage:$0 roleid level"
if [[ $roleid == "" || $level == "" ]]; then
	echo "arg error"
	exit 1
fi
date=`date +%Y%m%d%H%M%S`
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
echo "$date $0 $roleid $level" >> $log
(
	echo "admin login admin $passwd"
	sleep 1
	echo "role login $roleid"
	sleep 1
	echo "role setlevel $level"
	sleep 1
	echo "quit"
) | telnet localhost 3350
exit 0
