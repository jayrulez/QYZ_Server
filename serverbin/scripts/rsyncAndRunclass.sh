#!/bin/sh
cd `dirname $0`
pwd=`dirname $0`
#lx.gs.gm.TaskContent.class:"class : lx.gs.gm.TaskContent reload success"
function isok()
{
        ret=$1
        action="$2"
        if [[ $ret -ne 0 ]]; then
                echo  "ERROR!!!!,$action"
                exit 1
        else
                echo  "OK!!!!,$action"
                return 0
        fi
}
#rsync -azcp --delete /export/qyzyy/qyzyypackage/gsd/reload $pwd/../gsd/
#isok $? "rsync -azcp --delete /export/qyzyy/qyzyypackage/gsd/reload $pwd/../gsd/"
filename=$1
date=`date +%Y%m%d%H%M%S`
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
if [[ $filename == "" ]]; then
	echo "Usage:$0 filename"
	echo "Usage:$0 filename" >> $log
	exit 1
fi
echo "$date $0 $filename" >> $log
rm -rf $pwd/../gsd/scripts/*
isok $? "rm -rf $pwd/../gsd/scripts/*"
cp -rf /export/qyzyy/qyzyypackage/gsd/scripts/* $pwd/../gsd/scripts/
isok $? "/export/qyzyy/qyzyypackage/gsd/scripts/* $pwd/../gsd/scripts/"
file=$pwd/../gsd/scripts/$filename
if [[ ! -f $file ]]; then
	echo "file=$file not found"
	echo "file=$file not found" >> $log
	exit 1
fi
(
	sleep 1
	echo "admin login admin tswcbyy"
	sleep 1
	echo "script run $file"
	sleep 1
	echo "quit"
	sleep 1
)  | telnet localhost 3350 
exit 0
