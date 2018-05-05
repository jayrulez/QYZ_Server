#!/bin/sh

rm -rf link
cp -r ../serverbin/link .

rm -rf deliver
cp -r ../serverbin/deliver .

let x=0
let serverid=x

let gmport=33440+x
let rmiport=12100+2*x
let gsport=29000+x
let psport=10100+x

chmod +x link/daemon
chmod +x deliver/daemon

chmod +x link/xlink
chmod +x deliver/xdeliver

chmod +x start.sh
chmod +x status.sh
chmod +x stop.sh

rm -rf gsd
cp -r ../serverbin/gsd .
