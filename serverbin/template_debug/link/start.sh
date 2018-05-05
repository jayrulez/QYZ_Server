#!/bin/sh
cd `dirname $0`

ulimit -c unlimited
ulimit -n 32768 
WORKDIR=$PWD

$WORKDIR/daemon -f $WORKDIR/xlink.log -p $WORKDIR/xlink.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink.conf debug
echo xlink started

