#!/bin/sh
cd `dirname $0`

sh deliver/status.sh
sh link/status.sh
sh gsd/status.sh
serviced=/export/serviced
if [[ -d $serviced ]]; then
	cd $serviced
	sh status.sh
fi
exit 0
