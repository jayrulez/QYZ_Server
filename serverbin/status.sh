#!/bin/sh

cd `dirname $0`

sh deliver/status.sh
sh link/status.sh
sh gsd/status.sh
exit 0
