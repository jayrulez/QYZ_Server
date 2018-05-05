#!/bin/sh
#rm -rf gsd
mkdir xdb
mkdir xbackup
chmod +x daemon



let x=0
let gmport=33440+x
let rmiport=22100+2*x
let gsport=29000+x
let psport=10100+x

sed -i -e s/10010/$psport/ -e s/29000/$gsport/ gsd.xio.xml 

cp gsd.config.xml gsd.config.my.xml
