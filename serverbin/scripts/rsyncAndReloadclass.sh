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
rm -rf $pwd/../gsd/reload/*
isok $? "rm -rf $pwd/../gsd/reload/*"
cp -rf /export/qyzyy/qyzyypackage/gsd/reload/* $pwd/../gsd/reload/
isok $? "/export/qyzyy/qyzyypackage/gsd/reload/* $pwd/../gsd/reload/"
[[ /export/gmlog ]] && mkdir /export/gmlog
log=/export/gmlog/gm.log
echo "$date $0" >> $log
date=`date +%Y%m%d%H%M%S`
message=/export/gmlog/rsyncAndReloadclass.$date.log
(
	sleep 1
	echo "admin login admin tswcbyy"
	sleep 1
	echo "script reload"
	sleep 1
	echo "quit"
	sleep 1
)  | telnet localhost 3350  > $message 
cat $message | grep "reload success" > /dev/null 2>&1
if [[ $? -eq 0 ]]; then
	echo "grep reload success"
	exit 0
fi
echo "grep reload error"
exit 1
