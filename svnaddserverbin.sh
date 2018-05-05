#!/bin/sh
cd `dirname $0`
function isok()
{
        ret=$1
        action="$2"
        [[ $ret -ne 0 ]] && echo  "ERROR!!!!,$action" && exit 1
        echo  "OK!!!!,$action" && return 0
}
function docmd()
{
	cmd=$1
	echo $cmd
	eval "$cmd"
	isok $? "$cmd"
}
cd serverbin
isok $? "cd serverbin"
svn status | grep "?"
if [[ $? -eq 0 ]]; then
	svn status | awk '{if ($1 == "?") { print $2} }' | xargs svn add
	isok $? "svn status | awk {if ($1 == ?) { print $2} } | xargs svn add"
fi
svn ci -m "autocommit"
isok $? "svn ci -m autocommit"
#svn status > ../svncommit
#svn ci -F ../svncommit
#isok $? "svn ci -F ../svncommit"
