#!/bin/sh
roleid=$1
passwd="tswcbyy"
echo "Usage: $0 roleid"
if [[ $roleid == "" ]]; then
        echo "arg error"
        exit 1
fi
date=`date +%Y%m%d%H%M%S`
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
echo "$date $0 $roleid" >> $log
(
        echo "admin login admin $tswcbyy"
        sleep 1
        echo "role login $roleid"
        sleep 1
        echo "role queryroleinfo"
        sleep 1
        echo "quit"
)  | telnet localhost 3350
exit 0
