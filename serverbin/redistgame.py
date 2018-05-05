#!/bin/env python
# -*- coding: utf-8 -*-  
'''
qyz distribute script
'''
import os,sys
from optparse import OptionParser 
import subprocess
import socket
import time

#redistgame.py --auip 172.22.5.10 --auport 10000 --linkstartport 10001 --servicedip 127.0. 0.1 --servicedport 37000 --serverid 100 --powersupply true|false --gsdip gsdip
#return status
# 0 ok
# 1 args error
# 2 do shell error 
# 3 replace file error
# 4 ip or port error

def option():
    usage = "usage: %prog [options] arg"
    parser = OptionParser(usage) 
    parser.add_option("--powersupply", dest="powersupply", default="false", help="the power supply system") 
    parser.add_option("--auip", dest="auip", help="auany out ip") 
    parser.add_option("--auport", dest="auport", help="auany port") 
    parser.add_option("--linkstartport", dest="linkstartport", help="link start port") 
    parser.add_option("--servicedip", dest="servicedip", help="serviced out ip")
    parser.add_option("--servicedport", dest="servicedport", help="serviced port")
    parser.add_option("--serverid", dest="serverid", help="server id")
    parser.add_option("--gsdip", dest="gsdip", help="gsd ip")
    options, args = parser.parse_args()
    if options.powersupply and options.auip and options.auport and options.linkstartport and options.servicedip and options.servicedport and options.serverid and options.gsdip:
	return options
    else:
	parser.print_help()
        log("ERROR: parameter error.")
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
	log(cmd + ' ERROR!')
	sys.exit(2)
    else:
	log(cmd + ' OK!')

def docmd(cmd):
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copy(src, dst):
   src = packagehome + '/' + src
   cmd = 'cp -rf ' + src + ' ' + dst
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copyconfig():
   #link
   src = 'template_release/link/' 
   dst = dsthome + '/link/'
   copy(src + 'xlink1.my.conf', dst)
   copy(src + 'xlink2.my.conf', dst)
   copy(src + 'xlink3.my.conf', dst)
   copy(src + 'xlink4.my.conf', dst)
   copy(src + 'xlink5.my.conf', dst)
   copy(src + 'xlink6.my.conf', dst)
   #deliver
   src = 'template_release/deliver/' 
   dst = dsthome + '/deliver/'
   copy(src + 'xdeliver.my.conf', dst)
   #gsd
   src = 'template_release/gsd/' 
   dst = dsthome + '/gsd/'
   copy(src + 'gsd.config.my.xml', dst)
   copy(src + 'gsd.xio.my.xml', dst)

def rsyncdir():
   src = 'link'
   dst = dsthome + '/'
   rsync(src, dst)

   src = 'deliver'
   dst = dsthome + '/'
   rsync(src, dst)

   src = 'gsd'
   dst = dsthome + '/'
   rsync(src, dst)

   #rsync delete
   src = 'gsd/reload' 
   dst = dsthome + '/gsd/'
   rsyncdelete(src, dst)

   src = 'gsd/scripts' 
   dst = dsthome + '/gsd/'
   rsyncdelete(src, dst)

def chmod():
   os.chdir(dsthome)
   cmd = 'chmod 755 *.sh'
   docmd(cmd)
   os.chdir(dsthome + '/link/')
   cmd = 'chmod 755 xlink *.sh daemon'
   docmd(cmd)
   os.chdir(dsthome + '/deliver/')
   cmd = 'chmod 755 xdeliver *.sh daemon'
   docmd(cmd)
   os.chdir(dsthome + '/gsd/')
   cmd = 'chmod 755 *.sh daemon'
   docmd(cmd)
   #os.chdir('/root/')
   #cmd = 'chmod 755 *.sh'
   #docmd(cmd)
   os.chdir(packagehome)

def valid_port(port):
    if port >= 0 and port <= 65536:
        pass
    else:
        log("ERROR: valid_port %s" % (port))
        sys.exit(4)

def valid_ip(ip):
    try: 
        socket.inet_aton(ip)
    except:
        log("ERROR: valid_ip %s" % (ip))
        sys.exit(4)
   
def replace_file(file, src, dst):
    #try:
    log("replace file=%s src=%s dst=%s" % (file, src, dst))
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
        log("Error: not match, src=%s dst=%s" % (src, dst))
        sys.exit(3)

def replace():
    log("chdir /export/server_" + serverid)
    os.chdir('/export/server_' + serverid)
    # link
    replace_file('link/xlink1.my.conf', 'LINK_PORT1', LINK_PORT1)
    replace_file('link/xlink1.my.conf', 'PROVIDER_PORT1', PROVIDER_PORT1)
    replace_file('link/xlink1.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink1.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink1.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)

    replace_file('link/xlink2.my.conf', 'LINK_PORT2', LINK_PORT2)
    replace_file('link/xlink2.my.conf', 'PROVIDER_PORT2', PROVIDER_PORT2)
    replace_file('link/xlink2.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink2.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink2.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)

    replace_file('link/xlink3.my.conf', 'LINK_PORT3', LINK_PORT3)
    replace_file('link/xlink3.my.conf', 'PROVIDER_PORT3', PROVIDER_PORT3)
    replace_file('link/xlink3.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink3.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink3.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)

    replace_file('link/xlink4.my.conf', 'LINK_PORT4', LINK_PORT4)
    replace_file('link/xlink4.my.conf', 'PROVIDER_PORT4', PROVIDER_PORT4)
    replace_file('link/xlink4.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink4.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink4.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)

    replace_file('link/xlink5.my.conf', 'LINK_PORT5', LINK_PORT5)
    replace_file('link/xlink5.my.conf', 'PROVIDER_PORT5', PROVIDER_PORT5)
    replace_file('link/xlink5.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink5.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink5.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)

    replace_file('link/xlink6.my.conf', 'LINK_PORT6', LINK_PORT6)
    replace_file('link/xlink6.my.conf', 'PROVIDER_PORT6', PROVIDER_PORT6)
    replace_file('link/xlink6.my.conf', 'DELIVER_PORT', DELIVER_PORT)
    replace_file('link/xlink6.my.conf', 'LINK_MAX_USERS', LINK_MAX_USERS)
    replace_file('link/xlink6.my.conf', 'LINK_HALFLOGIN_USERS', LINK_HALFLOGIN_USERS)
    # deliver
    replace_file('deliver/xdeliver.my.conf', 'DELIVER_PORT', DELIVER_PORT) 
    replace_file('deliver/xdeliver.my.conf', 'AUANY_IP', AUANY_IP) 
    replace_file('deliver/xdeliver.my.conf', 'AUANY_PORT', AUANY_PORT) 
    replace_file('deliver/xdeliver.my.conf', 'SERVER_ID', SERVER_ID) 
    replace_file('deliver/xdeliver.my.conf', 'DELIVER_GS_PORT', DELIVER_GS_PORT)
    replace_file('deliver/xdeliver.my.conf', 'DELIVERY_MAX_PLAYER_NUM', DELIVERY_MAX_PLAYER_NUM)
    replace_file('deliver/xdeliver.my.conf', 'DELIVERY_FAKE_MAX_PLAYER_NUM', DELIVERY_FAKE_MAX_PLAYER_NUM)
    # gsd
    replace_file('gsd/gsd.config.my.xml', 'SERVER_ID',SERVER_ID )
    replace_file('gsd/gsd.config.my.xml', 'GSD_MAX_ONLINE',GSD_MAX_ONLINE )
    replace_file('gsd/gsd.config.my.xml', 'GSD_PORT',GSD_PORT )
    replace_file('gsd/gsd.config.my.xml', 'GSD_IP',GSD_IP)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT1', PROVIDER_PORT1)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT2', PROVIDER_PORT2)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT3', PROVIDER_PORT3)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT4', PROVIDER_PORT4)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT5', PROVIDER_PORT5)
    replace_file('gsd/gsd.xio.my.xml', 'PROVIDER_PORT6', PROVIDER_PORT6)
    replace_file('gsd/gsd.xio.my.xml', 'DELIVER_GS_PORT', DELIVER_GS_PORT)
    replace_file('gsd/gsd.xio.my.xml', 'SERVICED_IP',SERVICED_IP )
    replace_file('gsd/gsd.xio.my.xml', 'SERVICED_PORT', SERVICED_PORT)
    replace_file('gsd/gsd.xio.my.xml', 'GSD_PORT', GSD_PORT)
    if cmp(POWER_SUPPLY, "true") == 0:
        replace_file('gsd/start.sh', 'Xmx24g', 'Xmx54g')
    os.chdir(packagehome)

def dumpvar():
    log("POWER_SUPPLY=%s" % POWER_SUPPLY)
    log("SERVER_ID=%s" % SERVER_ID)
    log("START_PORT=%s" % START_PORT)
    log("AUANY_IP=%s" % AUANY_IP)
    log("AUANY_PORT=%s" % AUANY_PORT)
    log("SERVICED_IP=%s" % SERVICED_IP)
    log("SERVICED_PORT=%s" % SERVICED_PORT)
    log("LINK_PORT1=%s" % LINK_PORT1)
    log("LINK_PORT2=%s" % LINK_PORT2)
    log("LINK_PORT3=%s" % LINK_PORT3)
    log("LINK_PORT4=%s" % LINK_PORT1)
    log("LINK_PORT5=%s" % LINK_PORT2)
    log("LINK_PORT6=%s" % LINK_PORT3)
    log("PROVIDER_PORT1=%s" % PROVIDER_PORT1)
    log("PROVIDER_PORT2=%s" % PROVIDER_PORT2)
    log("PROVIDER_PORT3=%s" % PROVIDER_PORT3)
    log("PROVIDER_PORT4=%s" % PROVIDER_PORT1)
    log("PROVIDER_PORT5=%s" % PROVIDER_PORT2)
    log("PROVIDER_PORT6=%s" % PROVIDER_PORT3)
    log("DELIVER_PORT=%s" % DELIVER_PORT)
    log("DELIVER_GS_PORT=%s" % DELIVER_GS_PORT)
    log("GSD_PORT=%s" % GSD_PORT)

def stop():
    if os.path.exists(dsthome) == True:
        os.chdir(dsthome)
        cmd = dsthome + "/stop.sh"
        docmd(cmd)
        os.chdir(dsthome + '/gsd/')
        cmd = dsthome + "/gsd/stop.sh"
        docmd(cmd)
        os.chdir(packagehome)
    else:
        os.makedirs(dsthome)

def start():
    os.chdir(dsthome)
    cmd = dsthome + "/start.sh"
    docmd(cmd)
    os.chdir(dsthome + '/gsd/')
    cmd = dsthome + "/gsd/start.sh"
    docmd(cmd)
    os.chdir(dsthome)
    cmd = dsthome + "/status.sh"
    docmd(cmd)
    os.chdir(packagehome)

def ipdisable():
    if os.path.isfile('/root/ipdisable.sh'):
        cmd = 'sh /root/ipdisable.sh'
        docmd(cmd)

def ipenable():
    if os.path.isfile('/root/ipenable.sh'):
        cmd = 'sh /root/ipenable.sh'
        docmd(cmd)

def copyroot():
    src = packagehome + '/*.sh '
    cmd = 'cp -f ' + src + dsthome + "/"
    docmd(cmd)
    src = packagehome + "/scripts"
    if os.path.isdir(src):
        cmd = 'cp -rf ' + src + " " + dsthome + "/"
        docmd(cmd);

def cur_file_dir():
    path = sys.path[0]
    if os.path.isdir(path):
        return path
    elif os.path.isfile(path):
        return os.path.dirname(path)

def log(message):
    server = "redistgame"
    file = "/root/" + server + ".log"
    date = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    str = date + " [" + server + "] " + message
    print(str)
    open(file,'a+').writelines(str + "\n")

'''
port range:
auany 30100 - 30199
serviced  30200 - 30299
gsd 30400 - 30499
'''
#main begin
opt = option()
serverid = opt.serverid
packagehome = cur_file_dir()
dsthome = '/export/server_' + serverid
SERVER_ID = int(opt.serverid)
START_PORT = int(opt.linkstartport)
AUANY_IP = opt.auip
AUANY_PORT = int(opt.auport)
SERVICED_IP =opt.servicedip
SERVICED_PORT=int(opt.servicedport)
POWER_SUPPLY=opt.powersupply
GSD_IP=opt.gsdip

valid_ip(AUANY_IP)
valid_port(AUANY_PORT)
valid_ip(SERVICED_IP)
valid_port(SERVICED_PORT)
valid_port(START_PORT)
valid_ip(GSD_IP)

#link port = start_port + [0 - 10]
LINK_PORT1 = START_PORT
LINK_PORT2 = START_PORT + 1
LINK_PORT3 = START_PORT + 2
LINK_PORT4 = START_PORT + 3
LINK_PORT5 = START_PORT + 4
LINK_PORT6 = START_PORT + 5

#provider port = start_port + [10 - 20]
PROVIDER_PORT1 = START_PORT + 10 
PROVIDER_PORT2 = START_PORT + 11 
PROVIDER_PORT3 = START_PORT + 12 
PROVIDER_PORT4 = START_PORT + 13 
PROVIDER_PORT5 = START_PORT + 14 
PROVIDER_PORT6 = START_PORT + 15 

DELIVER_PORT = START_PORT + 20
DELIVER_GS_PORT = START_PORT + 21
GSD_PORT = START_PORT + 22

LINK_MAX_USERS=3000
LINK_HALFLOGIN_USERS=2000
DELIVERY_MAX_PLAYER_NUM=5000
DELIVERY_FAKE_MAX_PLAYER_NUM=3000
GSD_MAX_ONLINE=5000
if cmp(POWER_SUPPLY, "true") == 0:
    LINK_MAX_USERS=4000
    LINK_HALFLOGIN_USERS=3000
    DELIVERY_MAX_PLAYER_NUM =8000
    DELIVERY_FAKE_MAX_PLAYER_NUM=5000
    GSD_MAX_ONLINE=8000
   

ipdisable()
stop()
dumpvar()
rsyncdir()
copyconfig()
copyroot()
chmod()
replace()
start()
sys.exit(0)
