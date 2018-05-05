#!/bin/sh
roleid=$1
currency=$2
passwd="tswcbyy"
#type=10200001 gold coin	
#type=10200002 yuanbao
#type=10200003 bind yuanbao
echo "Usage: $0 roleid 10200003 bind_yuanbao"
if [[ $roleid == "" || $currency == "" ]]; then
        echo "arg error"
        exit 1
fi
date=`date +%Y%m%d%H%M%S`
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
echo "$date $0 $roleid $currency" >> $log
(
        echo "admin login admin $tswcbyy"
        sleep 1
        echo "role login $roleid"
        sleep 1
        echo "role addcurrency 10200003 $currency"
        sleep 1
        echo "quit"
)  | telnet localhost 3350 
