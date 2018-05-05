#!/bin/sh
function isok()
{
        ret=$1
        action="$2"
        #ret=1
        if [[ $ret -ne 0 ]]; then
                echo  "ERROR!!!!,$action"
                exit 1
        else
                echo  "OK!!!!,$action"
                return 0
        fi
}

serverid=$1
gsdip=$2
if [[ $serverid == "" && $gsdip == "" ]]; then
	echo "Usage $0 serverid gsdip"
	exit 1
fi
localhost=127.0.0.1
auip=120.92.19.28
auport=10000
linkstartport=10001
servicedip=$localhost
servicedport=36000
servicedglobalid=2000000
powersupply='false'
#serverid=4
#gsdip=$localhost
./redistgame.py --auip $auip --auport $auport --linkstartport $linkstartport --servicedip $servicedip --servicedport $servicedport --serverid $serverid --powersupply $powersupply --gsdip $gsdip
isok $?  "./redistgame.py --auip $auip --auport $auport --linkstartport $linkstartport --servicedip $servicedip --servicedport $servicedport --serverid $serverid --powersupply $powersupply --gsdip $gsdip"
./redistserviced.py  --servicedglobalid $servicedglobalid --servicedport $servicedport
isok $? "./redistserviced.py  --servicedglobalid $servicedglobalid --servicedport $servicedport"
#./redistauany.py --auport $auport
#isok $?  "./redistauany.py --auport $auport"
