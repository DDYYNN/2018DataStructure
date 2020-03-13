@echo off

if "%1" == "" goto :all
if "%1" == "all" goto :all
if "%1" == "essential" goto :essential
if "%1" == "changing" goto :changing
if "%1" == "aux" goto :aux
if "%1" == "clean" goto :clean
goto :error

:all
echo javac Main.java -Xlint:all -encoding utf-8
javac Main.java -Xlint:all -encoding utf-8
echo java -Xms1024M -Xmx1024M Main
java -Xms1024M -Xmx1024M Main
goto :end

:essential
echo javac Main.java -Xlint:all -encoding utf-8
javac Main.java -Xlint:all -encoding utf-8
echo java -Xms1024M -Xmx1024M Main basic random_seoul large1 large2
java -Xms1024M -Xmx1024M Main basic random_seoul large1 large2
goto :end

:changing
echo javac Main.java -Xlint:all -encoding utf-8
javac Main.java -Xlint:all -encoding utf-8
echo java -Xms1024M -Xmx1024M Main random_seoul_changing
java -Xms1024M -Xmx1024M Main random_seoul_changing
goto :end

:aux
echo javac Auxiliary.java -Xlint:all -encoding utf-8
javac Auxiliary.java -Xlint:all -encoding utf-8
goto :end

:clean
echo rm -f *.class y.out y.err
del *.class y.out y.err
goto :end

:error
echo make: *** No rule to make target `%1'.  Stop.

:end
