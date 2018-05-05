cd gsd
call build.bat
cd ..

copy /B /Y lib\libdb_amd64.so serverbin\gsd
copy /B /Y gsd\gsd.jar serverbin\gsd
copy /B /Y gsd\gsd.xio.xml serverbin\gsd
copy /B /Y gsd\gsd.xdb.xml serverbin\gsd
rd /S /Q serverbin\gsd\config
copy /B /Y gsd\coder.DeliverClient.xml serverbin\gsd
copy /B /Y gsd\coder.Provider.xml serverbin\gsd
xcopy /I /E gsd\config serverbin\gsd\config

pause