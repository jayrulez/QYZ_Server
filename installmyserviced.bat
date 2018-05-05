java -jar lib/rpcgen.jar -java protocol.serviced.xml -outputEncoding UTF8 -inputEncoding UTF8
cd serviced
java -jar ../../code/lib/config.jar -configxml ../../csv/cfg.xml -outputencoding UTF8 -lan java -codedir externs -datadir config -group server
call ant
cd ..

copy /B /Y serviced\serviced.jar serverbin\serviced\serviced.jar
copy /B /Y serviced\coder.ServiceServer.xml serverbin\serviced\coder.ServiceServer.xml
rd /S /Q serverbin\serviced\config
xcopy /I /E /Y serviced\config serverbin\serviced\config


pause


