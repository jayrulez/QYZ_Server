#!/bin/env python
# -*- coding: utf-8 -*-  
'''
qyz distribute script
'''
import os,sys
from optparse import OptionParser 
import subprocess
import socket

#redistmapd.py --mapdglobalid=1000000 --mapdip 127.0.0.1 --mapdport 36000 --servicedip 127.0.0.1 --servicedport 37000
def option():
    usage = "usage: %prog [options] arg"
    parser = OptionParser(usage) 
    parser.add_option("--mapdglobalid", dest="mapdglobalid", help="mapd global id")
    parser.add_option("--mapdip", dest="mapdip", help="mapd out ip")
    parser.add_option("--mapdport", dest="mapdport", help="mapd port")
    parser.add_option("--servicedip", dest="servicedip", help="serviced out ip")
    parser.add_option("--servicedport", dest="servicedport", help="serviced port")
    options, args = parser.parse_args()
    if options.mapdglobalid and options.mapdip and options.mapdport and options.servicedip and options.servicedport :
	return options
    else:
	parser.print_help()
        print("ERROR: parameter error.")
        sys.exit(1)    

def rsyncdelete(src, dst):
    space = ' '
    src = packagehome + '/' + src
    cmd = 'rsync --delete -avvzcpq' + space + src + space + dst
    ret = subprocess.call(cmd, shell=True)
    isok(ret, cmd)

def rsync(src, dst):
    space = ' '
    src = packagehome + '/' + src
    cmd = 'rsync -avvzcpq' + space + src + space + dst
    ret = subprocess.call(cmd, shell=True)
    isok(ret, cmd)
    
def isok(ret, cmd):
    if ret != 0:
	print 'do' + ' ' + cmd + ' ERROR!'
	sys.exit(1)
    else:
	print 'do' + ' ' + cmd + ' OK!'

def docmd(cmd):
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copy(src, dst):
   src = packagehome + '/' + src
   cmd = 'cp -rf ' + src + ' ' + dst
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copyconfig():
   src = 'template_release/mapd/' 
   dst = dsthome + '/'
   copy(src + 'mapd.config.my.xml', dst)
   copy(src + 'mapd.xio.my.xml', dst)

def rsyncdir():
   src = 'mapd'
   dst = '/export/'
   rsync(src, dst)

def chmod():
   os.chdir(dsthome)
   cmd = 'chmod 755 *.sh daemon'
   docmd(cmd)
   os.chdir('/root/')
   cmd = 'chmod 755 *.sh'
   docmd(cmd)

def valid_port(port):
    if port >= 0 and port <= 65536:
        pass
    else:
        print("ERROR: valid_port %s" % (port))
        sys.exit(1)

def valid_ip(ip):
    try: 
        socket.inet_aton(ip)
    except:
        print("ERROR: valid_ip %s" % (ip))
        sys.exit(1)
   
def replace_file(file, src, dst):
    #try:
    print("replace file=%s src=%s dst=%s" % (file, src, dst))
    lines=open(file,'r').readlines()
    flen=len(lines)-1
    found = False
    for i in range(flen):
        if src in lines[i]:
            lines[i]=lines[i].replace(src,str(dst))
            found = True
    if found == True:
        open(file,'w').writelines(lines)
    else:
        print("Error: not match, src=%s dst=%s" % (src, dst))
        sys.exit(3)

    #except Exception,e:
    #    print e

def replace():
    file = dsthome + "/mapd.xio.my.xml"
    replace_file(file, 'MAPD_PORT', MAPD_PORT)
    replace_file(file, 'SERVICED_IP', SERVICED_IP)
    replace_file(file, 'SERVICED_PORT', SERVICED_PORT)
    file = dsthome + "/mapd.config.my.xml"
    replace_file(file, 'MAPD_GLOBAL_ID', MAPD_GLOBAL_ID)
    replace_file(file, 'MAPD_IP', MAPD_IP)
    replace_file(file, 'MAPD_PORT', MAPD_PORT)

def dump():
    print("MAPD_GLOBAL_ID=%s" % MAPD_GLOBAL_ID)
    print("MAPD_IP=%s" % MAPD_IP)
    print("MAPD_PORT=%s" % MAPD_PORT)
    print("SERVICED_IP=%s" % SERVICED_IP)
    print("SERVICED_PORT=%s" % SERVICED_PORT)

def stop():
    if os.path.exists(dsthome) == True:
        os.chdir('/export/mapd/')
        cmd = dsthome + "/stop.sh"
        docmd(cmd) 
        os.chdir(packagehome)
    
def start():
    os.chdir(dsthome)
    cmd = dsthome + "/start.sh"
    docmd(cmd)
    cmd = dsthome + "/status.sh"
    docmd(cmd)
    os.chdir(packagehome)

def cur_file_dir():
    path = sys.path[0]
    if os.path.isdir(path):
        return path
    elif os.path.isfile(path):
        return os.path.dirname(path)

'''
port range:
auany 30100 - 30199
serviced  30200 - 30299
gmapd 30300 - 30399
gsd 30400 - 30499
mapdglobalid 1000000 - 1999999
servicedglobalid 2000000 - 2999999
'''
#main
opt = option()
packagehome = cur_file_dir()
dsthome = '/export/mapd'
MAPD_GLOBAL_ID = int(opt.mapdglobalid)
MAPD_IP = opt.mapdip
MAPD_PORT = int(opt.mapdport)
SERVICED_IP =opt.servicedip
SERVICED_PORT=int(opt.servicedport)

valid_ip(MAPD_IP)
valid_port(MAPD_PORT)
valid_ip(SERVICED_IP)
valid_port(SERVICED_PORT)
if MAPD_GLOBAL_ID < 1000000 or MAPD_GLOBAL_ID > 2000000:
    print('mapd globalid error')
    sys.exit(1)

stop()
dump()
rsyncdir()
copyconfig()
chmod()
replace()
start()
sys.exit(0)
