#!/bin/sh
cd `dirname $0`
pwd=`dirname $0`

begin=`date +%Y%m%d%H%M%S`
function isok() {
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && exit 1
        echo  "OK!!!!,$action" && return 0
}
function docmd() {
	cmd="$1"
	echo "$cmd"
	eval "$cmd"
	isok $? "$cmd"
}

docmd "./svnup.sh"
docmd "./installall.sh"
docmd "./svnaddserverbin.sh"
