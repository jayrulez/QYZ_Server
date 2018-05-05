#!/bin/sh
cd `dirname $0`
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
function docmd()
{
	cmd="$1"
	echo "$cmd"
	eval "$cmd"
	isok $? "$cmd"
}
cd ..
docmd "java -jar lib/rpcgen.jar -java protocol.serviced.xml -outputEncoding UTF8 -inputEncoding UTF8"
cd -
docmd "ant clean"
docmd "ant"
