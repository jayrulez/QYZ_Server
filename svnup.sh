#!/bin/sh
cd `dirname $0`
pwd=`pwd`

date=`date +%Y%m%d%H%M%S`
log="$pwd/svnup.log"
errorlog="$pwd/svnuperror.log"
function isok() {
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && echo "ERROR!!!!, $action" >> $log && exit 1
        echo  "OK!!!!,$action" && echo "OK!!!!, $action" >> $log && return 0
}
function docmd() {
	cmd=$1
	echo $cmd 
	echo $cmd  >> $log
	echo $cmd  >> $errorlog
	eval "$cmd 1>>$log 2>>$errorlog"
	isok $? "$cmd"
}
echo "install begin:$date" > $log
echo "install begin:$date" > $errorlog
echo $pwd | grep branches > /dev/null
if [[ $? -eq 0 ]]; then
	version="dev"
else 
	version=""
fi
if [[ $version == "dev" ]]; then
	docmd "svn up"
	docmd "svn up ../../../luxianres/branches/test826/csv"
	docmd "svn up ../../../luxianres/branches/test826/code"
	docmd "svn up ../../../luxianres/branches/test826/GameWindows4.2.0"
else
	docmd "svn up"
	docmd "svn up ../../../luxianres/trunk/csv"
	docmd "svn up ../../../luxianres/trunk/code"
	docmd "svn up ../../../luxianres/trunk/GameWindows4.2.0"
fi 
date=`date +%Y%m%d%H%M%S`
echo "install end:$date" >> $log
echo "install end:$date" >> $errorlog
