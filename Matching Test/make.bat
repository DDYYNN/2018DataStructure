@echo off

if "%1" == "" goto :all
if "%1" == "all" goto :all
if "%1" == "test" goto :test
if "%1" == "rolling" goto :rolling
if "%1" == "clean" goto :clean
goto :error

:all
echo javac Matching.java Main.java -encoding utf-8
javac Matching.java Main.java -encoding utf-8
echo java Main
java Main
goto :end

:test
echo javac Matching.java Main.java -encoding utf-8
javac Matching.java Main.java -encoding utf-8
echo java Main 1
java Main 1
goto :end

:rolling
echo javac Matching.java Main.java -encoding utf-8
javac Matching.java Main.java -encoding utf-8
echo java Main 8
java Main 8
goto :end

:clean
echo rm -f *.class y.out y.err
del *.class y.out y.err
goto :end

:error
echo make: *** No rule to make target `%1'.  Stop.

:end
