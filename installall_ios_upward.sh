#!/bin/sh
cd `dirname $0`
pwd=`dirname $0`

date=`date +%Y%m%d%H%M%S`
log="$pwd/installall.log"
errorlog="$pwd/installallerror.log"
function isok() {
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && echo  "ERROR!!!!,$action" >> $log && exit 1
        echo  "OK!!!!,$action" && echo "OK!!!!,$action" >> $log && return 0
}
function docmd() {
	cmd="$1"
	echo "$cmd"
	echo "$cmd" >> $log
	echo "$cmd" >> $errorlog
	eval "$cmd 1>>$log 2>>$errorlog"
	isok $? "$cmd"
}

echo "install begin:$date" > $log
echo "install begin:$date" > $errorlog

mv ../../../luxianres/branches/test826/csv/pay/charge.xlsx shell/charge.xlsx.svn
/bin/cp -f shell/charge.xlsx ../../../luxianres/branches/test826/csv/pay/
isok $?  "/bin/cp -f shell/charge.xlsx ../../../luxianres/branches/test826/csv/pay/"

docmd "./installgsd.sh"

/bin/cp -f shell/charge.xlsx.svn ../../../luxianres/branches/test826/csv/pay/charge.xlsx

#docmd "./installauany.sh"

cd serviced
docmd "./buildjar.sh"
cd -
#docmd "/bin/cp -f serviced/serviced.jar serverbin/serviced/"
/bin/cp -f serviced/serviced.jar serverbin/serviced/
isok $?  "/bin/cp -f serviced/serviced.jar serverbin/serviced/"
/bin/cp -f serviced/coder.ServiceServer.xml serverbin/serviced/
isok $?  "/bin/cp -f serviced/coder.ServiceServer.xml serverbin/serviced/"
docmd "rsync -azcp gsd/config serverbin/serviced/"

date=`date +%Y%m%d%H%M%S`
echo "install end:$date" >> $log
echo "install end:$date" >> $errorlog
