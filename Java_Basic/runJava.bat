@ECHO OFF  
cd %~dp1  
ECHO Compiling %~nx1.......  
IF EXIST %~n1.class (  
DEL %~n1.class  
)  
javac %~nx1  
IF EXIST %~n1.class (  
ECHO -----------OUTPUT-----------  
java %~n1  
)  