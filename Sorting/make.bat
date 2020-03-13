@echo off

if "%1" == "" goto :all
if "%1" == "all" goto :all
if "%1" == "clean" goto :clean
goto :error

:all
echo javac Util.java -encoding utf-8
javac Util.java
echo java Util
java Util
echo java Main
java Main
goto :end

:clean
echo rm -f *.class y.out y.err compile.err
del *.class y.out y.err compile.err
goto :end

:error
echo make: *** No rule to make target `%1'.  Stop.

:end
