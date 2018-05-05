#!/bin/sh
cd `dirname $0`
sh deliver/stop.sh
sh link/stop.sh
sh gsd/stop.sh
