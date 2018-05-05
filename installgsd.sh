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
cd gsd
docmd "./build.sh;"
cd -
docmd "cp -f lib/libdb_amd64.so serverbin/gsd"
docmd "cp -f gsd/gsd.jar serverbin/gsd"
docmd "cp -f gsd/gsd.xdb.xml serverbin/gsd/"
docmd "cp -f gsd/coder.DeliverClient.xml serverbin/gsd/"
docmd "cp -f gsd/coder.Provider.xml serverbin/gsd/"
docmd "rsync -azcp gsd/config serverbin/gsd/"
#docmd "cp -f gsd/gsd.xio.xml serverbin/template_release/gsd/"
