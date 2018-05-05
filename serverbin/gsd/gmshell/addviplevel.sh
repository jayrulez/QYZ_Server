#!/bin/sh
roleid=$1
level=$2
echo "Usage: $0 roleid level"
if [[ $roleid == "" || $level == "" ]]; then
        echo "arg error"
        exit 1
fi
(
        sleep 1
        echo "admin login admin tswcbyy"
        sleep 1
        echo "role login $roleid"
        sleep 1
        echo "role setviplevel $level"
        sleep 1
        echo "quit"
)  | telnet localhost 3350
