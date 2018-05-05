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
cd serviced
docmd "./build.sh"
cd -
docmd "cp -f serviced/serviced.jar serverbin/serviced/"
#docmd "cp -f serviced/serviced.xio.xml serverbin/template_release/serviced/"
#docmd "cp -f serviced/serviced.config.xml serverbin/serviced/"
docmd "cp -f serviced/coder.ServiceServer.xml serverbin/serviced/"
docmd "rsync -azcp serviced/config serverbin/serviced/"
