#!/bin/sh
roleid=$1
currency=$2
type=$3
echo "Usage: $0 roleid currency type"
#10200001	金币
#10200002	元宝
#10200003	绑定元宝
if [[ $roleid == "" || $currency == "" || $type == "" ]]; then
        echo "arg error"
        exit 1
fi
(
        sleep 1
        echo "admin login admin tswcbyy"
        sleep 1
        echo "role login $roleid"
        sleep 1
        echo "role addcurrency $type $currency"
        sleep 1
        echo "quit"
)  | telnet localhost 3350
