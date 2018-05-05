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

docmd "cd ..; java -jar lib/rpcgen.jar -java gnet.xauany.xml; cd -"
docmd "java -jar ../lib/xdb.jar xauany.xdb.xml"
docmd "ant"
