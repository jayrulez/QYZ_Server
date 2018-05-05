#!/bin/sh
cd `dirname $0`
pwd=`pwd`

log="$pwd/svninfo.txt"
function isok() {
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && exit 1
        echo  "OK!!!!,$action" && return 0
}
function docmd() {
	cmd=$1
	echo $cmd 
	isok $? "$cmd"
}

echo $pwd | grep branches > /dev/null
if [[ $? -eq 0 ]]; then
	version="dev"
else 
	version=""
fi
if [[ $version == "dev" ]]; then
	echo "svn info luxian/branches/dev/" > $log
	svn info >> $log
	isok $? "svn info luxian/branches/dev/"
	echo "svn info luxianres/branches/test826/csv/" >> $log
	svn info ../../../luxianres/branches/test826/csv/ >> $log
	isok $? "svn info luxianres/branches/test826/csv/"
else
	echo "svn info luxian/trunk/dev/" > $log
	svn info >> $log
	isok $? "svn info luxian/trunk/dev/"
	echo "svn info luxianres/trunk/csv/" >> $log
	svn info ../../../luxianres/trunk/csv/ >> $log
	isok $? "svn info luxianres/trunk/csv/"
fi
