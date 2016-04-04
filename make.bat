@echo off
cls

echo ******* wiki make app *******

echo ******* call config.bat *******
call config.bat


if exist %COMP_PATH% rmdir %COMP_PATH% /s /q
if exist %RELEASE_PATH% rmdir %RELEASE_PATH% /s /q
if exist tmp rmdir tmp /s /q
md %COMP_PATH%
md %RELEASE_PATH%
md tmp

copy /y %SRC_PATH%\*.* tmp\
echo *************** make class ***************
"%JAVA_HOME%\bin\javac.exe" -classpath %CLASS_PATH% tmp\*.java -d %COMP_PATH%
rmdir tmp /s /q

echo *************** make jar ***************
"%JAVA_HOME%\bin\jar.exe" cfM "%PRJ_NAME%_%VERSION%.jar" -C "%COMP_PATH%" .
copy %PRJ_NAME%_%VERSION%.jar %RELEASE_PATH%\
copy /y %PRJ_NAME%_%VERSION%.jar D:\Projects\Android2016\AndroidGameCanvas\trunk\libs\
del %PRJ_NAME%_%VERSION%.jar