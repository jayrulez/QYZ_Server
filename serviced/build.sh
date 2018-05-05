#!/bin/sh
cd `dirname $0`
pwd=`pwd`
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
echo $pwd | grep branches > /dev/null
if [[ $? -eq 0 ]]; then
	version="dev"
else 
	version=""
fi
if [[ $version == "dev" ]]; then
	docmd "java -jar ../../../../luxianres/branches/test826/code/lib/config.jar -configxml ../../../../luxianres/branches/test826/csv/cfg.xml -outputencoding UTF8 -lan java -codedir externs -datadir config -group server"
else
	docmd "java -jar ../../../../luxianres/trunk/code/lib/config.jar -configxml ../../../../luxianres/trunk/csv/cfg.xml -outputencoding UTF8 -lan java -codedir externs -datadir config -group server"
fi
cd ..
docmd "java -jar lib/rpcgen.jar -java protocol.serviced.xml -outputEncoding UTF8 -inputEncoding UTF8"
cd -
docmd "ant clean"
docmd "ant"
