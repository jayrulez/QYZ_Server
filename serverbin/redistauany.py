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

#redistauany.py --auport 10000
#return status
# 0 ok
# 1 args error
# 2 do shell error 
# 3 replace file error
# 4 ip or port error

def option():
    usage = "usage: %prog [options] arg"
    parser = OptionParser(usage) 
    parser.add_option("--auport", dest="auport", help="auany port") 
    options, args = parser.parse_args()
    if options.auport :
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
	log('do' + ' ' + cmd + ' ERROR!')
	sys.exit(2)
    else:
	log('do' + ' ' + cmd + ' OK!')

def docmd(cmd):
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copy(src, dst):
   src = packagehome + '/' + src
   cmd = 'cp -rf ' + src + ' ' + dst
   ret = subprocess.call(cmd, shell=True)
   isok(ret, cmd)

def copyconfig():
   src = 'template_release/auany/' 
   dst = dsthome + '/'
   copy(src + 'xauany.config.my.xml', dst)
   copy(src + 'xauany.xio.my.xml', dst)

def rsyncdir():
   src = 'auany'
   dst = '/export/'
   rsync(src, dst)

def chmod():
   os.chdir(dsthome)
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
        sys.exit(1)

def valid_ip(ip):
    try: 
        socket.inet_aton(ip)
    except:
        log("ERROR: valid_ip %s" % (ip))
        sys.exit(1)
   
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
    file = dsthome + "/xauany.xio.my.xml"
    replace_file(file, 'AUANY_PORT', AUANY_PORT)

def dump():
    log("AUANY_PORT=%s" % AUANY_PORT)

def stop():
    if os.path.exists(dsthome) == True:
        os.chdir('/export/auany/')
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

def log(message):
    server = "redistauany"
    file = "/root/" + server + ".log"
    date = time.strftime('%Y-%m-%d %H:%M:%S',time.localtime(time.time()))
    str = date + " [" + server + "] " + message
    print(str)
    open(file,'a+').writelines(str + "\n")

'''
port range:
auany 30100 - 30199
serviced  30200 - 30299
gmapd 30300 - 30399
gsd 30400 - 30499
mapdglobalid 1000000 - 1999999
servicedglobalid 2000000 - 2999999
'''
opt = option()
packagehome = cur_file_dir()
dsthome = '/export/auany'
AUANY_PORT = int(opt.auport)
valid_port(AUANY_PORT)

#stop()
dump()
rsyncdir()
copyconfig()
chmod()
replace()
#start()
sys.exit(0)
