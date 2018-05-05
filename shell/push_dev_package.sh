#!/bin/sh
cd `dirname $0`
function isok()
{
        ret=$1
        action="$2"
        #ret=1
        if [[ $ret -ne 0 ]]; then
                echo  "ERROR!!!!,$action"
                exit 1
        else
                echo  "OK!!!!,$action"
                return 0
        fi
}
NAME=$1
FULL_NAME=${NAME}package
echo export $FULL_NAME begin...
date=`date +%Y%m%d%H%M%S`
backup=${FULL_NAME}.dev.bak
if [[ ! -d $backup ]]; then
	mkdir $backup
fi
mv $FULL_NAME $backup/$date
isok $?  "mv $FULL_NAME $backup/$date"
svn export svn://diskstation/luxian/branches/dev/serverbin $FULL_NAME > /dev/null
isok $? "svn export svn://diskstation/luxian/branches/dev/serverbin $FULL_NAME > /dev/null"
cd $FULL_NAME
chmod 755 *.py
chmod 755 *.sh
find . -name .svn |xargs rm -rf
isok $?  "rm -rf .svn"
echo make md5 begin ...
find ./ -type f | xargs md5sum | awk '{print $2,":",$1}' | cut -c 3-100000 > $FULL_NAME.md5;sed -i 's/ : /:/g' $FULL_NAME.md5
isok $? "gen md5sum"
echo "$date"
echo "$date"  > $FULL_NAME.date
cd -
echo sync  begin ...
rsync --password-file=passwd/$NAME.passwd -avvzcp --delete $FULL_NAME ${NAME}@mobileserver.autopatch.173.com::${NAME}
isok $? "rsync --password-file=passwd/$NAME.passwd -avvzcp --delete $FULL_NAME ${NAME}@mobileserver.autopatch.173.com::${NAME}"
echo end.
echo "rsync date=$date"
