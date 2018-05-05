#!/bin/sh
cd `dirname $0`
WORK_DIR=$PWD
cd ../..
PARENT_DIR=$PWD

MAIN_DIR=${PWD}/serverbin

cd $MAIN_DIR
echo 
svn up
echo "svn up servebin finished!!!"

cd ${WORK_DIR}
echo "server stoping..."
sh stop.sh
cp -rf $MAIN_DIR/gsd/* .
echo "server starting..."

sh start.sh
sh status.sh

if [ -f "gsd.pid" ] 
then
	echo " restart succeed"
else
	echo "restart failed!!!"
fi

