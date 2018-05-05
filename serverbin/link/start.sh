#!/bin/sh
cd `dirname $0`

ulimit -c unlimited
ulimit -n 32768
WORKDIR=$PWD

$WORKDIR/daemon -f $WORKDIR/xlink1.log -p $WORKDIR/xlink1.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink1.my.conf
$WORKDIR/daemon -f $WORKDIR/xlink2.log -p $WORKDIR/xlink2.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink2.my.conf
$WORKDIR/daemon -f $WORKDIR/xlink3.log -p $WORKDIR/xlink3.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink3.my.conf
$WORKDIR/daemon -f $WORKDIR/xlink4.log -p $WORKDIR/xlink4.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink4.my.conf
$WORKDIR/daemon -f $WORKDIR/xlink5.log -p $WORKDIR/xlink5.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink5.my.conf
$WORKDIR/daemon -f $WORKDIR/xlink6.log -p $WORKDIR/xlink6.pid -c $WORKDIR $WORKDIR/xlink $WORKDIR/xlink6.my.conf
echo xlink started
