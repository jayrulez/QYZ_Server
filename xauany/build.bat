cd ..
java -jar lib/rpcgen.jar -java gnet.xauany.xml
cd xauany
java -jar ../lib/xdb.jar xauany.xdb.xml
ant
pause
