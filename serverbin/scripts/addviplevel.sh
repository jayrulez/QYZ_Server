#!/bin/sh
roleid=$1
level=$2
passwd="tswcbyy"
echo "Usage: $0 roleid level"
if [[ $roleid == "" || $level == "" ]]; then
        echo "arg error"
        exit 1
fi
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
echo "addviplevel $roleid $level" >> $log
(
        echo "admin login admin $passwd"
        sleep 1
        echo "role login $roleid"
        sleep 1
        echo "role setviplevel $level"
        sleep 1
        echo "quit"
)  | telnet localhost 3350
exit 0
