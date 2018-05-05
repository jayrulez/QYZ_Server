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
diffaction=$1
src=$2
dst=$3
home="/export/home/root/lxmobile"
if [[ $diffaction == "" || $src == "" || $dst == "" ]]; then
	echo "Usage:$0 diffaction srcdir dstdir"
	exit 1
fi
function jarxf() {
	insrc=$1
	server=$2
	if [[ $server == "" || $insrc == "" ]]; then
		echo "jarxf() server or insrc is null"
		exit 1
	fi
	if [[ -d $insrc/$server/${server}class ]]; then
		rm -rf $insrc/$server/${server}class
		isok $? "rm -rf $insrc/$server/${server}class"
	fi
	mkdir -p $insrc/$server/${server}class
	isok $? "mkdir -p $insrc/$server/${server}class"
	cp $insrc/$server/$server.jar $insrc/$server/${server}class/
	isok $? "/bin/cp $insrc/$server/$server.jar $insrc/$server/${server}class/"
	cd $insrc/$server/${server}class/
	isok $? "cd $insrc/$server/${server}class/"
	jar xf $server.jar
	isok $? "jar xf $server.jar"
	rm -f $insrc/$server/${server}class/${server}.jar
	isok $? "rm -f $insrc/$server/${server}class/${server}.jar"
	cd -
}

function clearxf() {
	insrc=$1
	server=$2
	if [[ $insrc == "" || $server == "" ]]; then
		echo "clearxf() server or insrc is null"
		exit 1
	fi
	/bin/rm -rf $insrc/$server/${server}class
	isok $?  "/bin/rm -rf $insrc/$server/${server}class"
}
jarxf "$src" "gsd"
jarxf "$src" "serviced"
jarxf "$dst" "gsd"
jarxf "$dst" "serviced"
echo "diff -rq $src $dst > $home/alltmp/$diffaction.diff"
diff -rq $src $dst > $home/alltmp/$diffaction.diff
#clearxf "$src" "gsd"
#clearxf "$src" "serviced"
#clearxf "$dst" "gsd"
#clearxf "$dst" "serviced"

exit 0
