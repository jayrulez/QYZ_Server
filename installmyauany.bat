cd xauany
call build.bat
cd ..

copy /B /Y xauany\xauany.jar serverbin\auany
copy /B /Y xauany\xauany.xdb.xml serverbin\auany
copy /B /Y xauany\coder.AuthServer.xml serverbin\auany

pause