#!/bin/sh
cd `dirname $0`
cmdhome=`dirname $0`
echo "cd $cmdhome"
sh deliver/start.sh
sh link/start.sh
sh gsd/start.sh
