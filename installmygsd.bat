TortoiseProc /command:update /path:"."
TortoiseProc /command:update /path:"../csv"

java -jar lib/rpcgen.jar -java protocol.gsd.xml -outputEncoding UTF8 -inputEncoding UTF8
cd gsd
java -jar ../code/lib/config.jar -configxml ../csv/cfg.xml -outputencoding UTF8 -lan java -codedir externs -datadir config -group server
java -jar ../lib/xdb.jar -outputEncoding UTF8 gsd.xdb.xml
call ant
cd ..

copy /B /Y gsd\gsd.jar serverbin\gsd
copy /B /Y gsd\gsd.xdb.xml serverbin\gsd
copy /B /Y gsd\coder.DeliverClient.xml serverbin\gsd
copy /B /Y gsd\coder.Provider.xml serverbin\gsd
copy /B /Y gsd\coder.MapClient.xml serverbin\gsd
copy /B /Y gsd\coder.ServiceClient.xml serverbin\gsd
rd /S /Q serverbin\gsd\config
xcopy /I /E /Y gsd\config serverbin\gsd\config

set TARGET=..\serverbin

copy /y gsd\gsd.jar  %TARGET%
copy /y gsd\gsd.config.xml %TARGET%
copy /y  gsd\gsd.xio.xml %TARGET%
copy /y  gsd\gsd.xdb.xml %TARGET%
copy /y  gsd\coder.DeliverClient.xml %TARGET%
copy /y  gsd\coder.Provider.xml %TARGET%
copy /y  gsd\coder.MapClient.xml %TARGET%
copy /y  gsd\coder.ServiceClient.xml %TARGET%

pause


