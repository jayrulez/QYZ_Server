#!/bin/sh
function isok() {
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && exit 1
        echo  "OK!!!!,$action" && return 0
}
function docmd() {
	cmd=$1
	echo $cmd
	eval "$cmd"
	isok $? "$cmd"
}
cd `dirname $0`
docmd "cd xauany; ./build.sh; cd -;"
docmd "cp -f xauany/xauany.jar serverbin/auany/"
docmd "cp -f xauany/xauany.xdb.xml serverbin/auany/"
docmd "cp -f xauany/coder.AuthServer.xml serverbin/auany"
