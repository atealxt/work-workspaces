@echo off
title EasyJF
color a
rem by WilliamRaym 11:54 2007-12-18
if "%OS%"=="Windows_NT" SETLOCAL
:init
if "%JAVA_HOME%"=="" goto nojava
goto main
goto eof

:main
if not exist ../src/core/src/main/java/com/easyjf/web/easyjf-web.xml (
@set ROOT=0
@call setenv.bat
) else (
@set ROOT=1

)

if "%1"=="war" goto war
if "%1"=="run" goto run
if "%1"=="crud" goto crud
if "%1"=="project" goto project

if "%1"=="" goto usage
goto eof


:usage
@echo ʹ�÷�����
@echo easyjweb war							���������ǰ���̳�һ��war��
@echo easyjweb run							������Mavenֱ�����б�����
@echo easyjweb crud org.easyjf.domain.Example				��������һ�����������������Ӧ��
@echo easyjweb crud  org.easyjf.domain.Example ../src/main/java/org/easyjf/domain/Example.java	��������һ��JAVAԴ�ļ��������������Ӧ��
@echo ��������crudֻ��crud�ļ������ʹ�÷�����������ּ���ǣ���һ��JAVA�࣬�������JAVA��������Ӧ�������ļ���
@echo.
@echo ��Ŀ¼Ϊeasyjweb�ĸ�Ŀ¼ʱ
@echo easyjweb project d:\workspace\myapp				������d:\workspace\������һ����Ϊmyapp�ļ�MVCӦ��
@echo easyjweb project d:\workspace\myapp -ejs			������d:\workspace\������һ����Ϊmyapp��Ӧ��
@echo ����ĿΪ��Easyjweb + JPA + Spring���Ľṹ
@echo easyjweb project d:\workspace\myapp -ejs -maven			������d:\workspace\������һ����Ϊmyapp���й���Maven��Ӧ��
goto eof

:crud
@if "1"=="%ROOT%" @goto isEASYJWEBPROJECT
if "%2"=="" goto usage
@echo start crud %2
title ����%2 CRUD
if not "%3"=="" @javac %3 -d ../src/main/webapp/WEB-INF/classes/ -encoding UTF-8
java com.easyjf.generator.Generator %2
goto eof

:project
if "%2"=="" goto usage
if "0"=="%ROOT%" goto notEASYJWEBPROJECT
rem ../src/core/src/main/java/com/easyjf/web/easyjf-web.xml goto notEASYJWEBPROJECT
if "%4"=="-maven" goto projectEJSMAVEN
if "%4"=="maven" goto projectEJSMAVEN
if "%4"=="-mvn" goto projectEJSMAVEN
if "%4"=="mvn" goto projectEJSMAVEN
if "%3"=="ejs" goto projectEJS
if "%3"=="-ejs" goto projectEJS

@echo ��ʼ����MINI��Ŀ%2
title ����%2 MINI Project
if exist "%2" @echo %2�Ѵ����ˣ�Ϊ�˰�ȫ���������ɾ����Ŀ¼��ָ��һ�������ڵ�Ŀ¼��
if exist "%2" goto eof
if not exist "%2" md "%2"
@xcopy "templates\mini" "%2" /E /C /F /I /Q /R /K /Y >nul 2>nul
@rem copy the easyjweb jars to target dir
@copy "..\lib\easyjweb-core-*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "..\lib\required\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "lib\build\*.jar" "%2\bin\lib\build\" >nul 2>nul
@copy "*.bat" "%2\bin\" >nul 2>nul
@echo �ɹ���ɣ����ڴ�Ŀ��Ŀ¼
@explorer "%2"
goto eof

:projectEJS
if "%3"=="" goto usage
@echo ��ʼ����Easyjweb JPA Spring Project��Ŀ %2
title ����%2 Easyjweb JPA Spring Project
if exist "%2" @echo %2�Ѵ����ˣ�Ϊ�˰�ȫ���������ɾ����Ŀ¼��ָ��һ�������ڵ�Ŀ¼��
if exist "%2" goto eof
if not exist "%2" md "%2"
@xcopy "templates\ejs" "%2" /E /C /F /I /Q /R /K /Y >nul 2>nul
@rem copy the easyjweb jars to target dir
@copy "..\lib\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "..\lib\required\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul

@copy "..\lib\jpa\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "..\lib\spring\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "..\lib\other\*.jar" "%2\src\main\webapp\WEB-INF\lib\" >nul 2>nul
@copy "lib\build\*.jar" "%2\bin\lib\build\" >nul 2>nul
@del "%2\src\main\webapp\WEB-INF\lib\servlet-api-2.5-6.1.4.jar" /q >nul 2>nul
@del "%2\pom.xml" /q

@copy "*.bat" "%2\bin\" >nul 2>nul
@echo �ɹ���ɣ����ڴ�Ŀ��Ŀ¼
@explorer "%2"
goto eof

:projectEJSMAVEN
if "%3"=="" goto usage
if "%4"=="" goto usage
@echo ��ʼ����Easyjweb JPA Spring Project��Ŀ����ʹ��MAVEN������Ŀ�� %2
title ����%2 Easyjweb JPA Spring Project
if exist "%2" @echo %2�Ѵ����ˣ�Ϊ�˰�ȫ���������ɾ����Ŀ¼��ָ��һ�������ڵ�Ŀ¼��
if exist "%2" goto eof
if not exist "%2" md "%2"
@xcopy "templates\ejs" "%2" /E /C /F /I /Q /R /K /Y >nul 2>nul
@rem copy the easyjweb jars to target dir
@copy "lib\build\*.jar" "%2\bin\lib\build\" >nul 2>nul

@copy "*.bat" "%2\bin\" >nul 2>nul
@echo �ɹ���ɣ����ڴ�Ŀ��Ŀ¼
@explorer "%2"
goto eof

:war
@if "1"=="%ROOT%" @goto isEASYJWEBPROJECT
@call build war
goto eof

:run
@if "1"=="%ROOT%" @goto isEASYJWEBPROJECT
@echo ��ʼ���б���Ŀ
cd ..
@mvn jetty:run
goto eof


:nojava
@echo �����Ĳ���ϵͳ��û�а�װJAVA���л�������������JAVA_HOME����������װJDK
goto eof

:isEASYJWEBPROJECT
@echo ����Ŀ��EasyJWeb��Ŀ�������ڴ�ִ�е�ǰ���
@echo.
@echo �˴�����ִ�е������У�
@echo easyjweb project d:\workspace\myapp				������d:\workspace\������һ����Ϊmyapp�ļ�MVCӦ��
@echo easyjweb project d:\workspace\myapp -ejs			������d:\workspace\������һ����Ϊmyapp��Ӧ��
@echo ����ĿΪ��Easyjweb + JPA + Spring���Ľṹ
@echo easyjweb project d:\workspace\myapp -ejs -maven			������d:\workspace\������һ����Ϊmyapp���й���Maven��Ӧ��
goto eof

:notEASYJWEBPROJECT
@echo ����Ŀ����EasyJWeb��Ŀ������Easyjweb��Ŀ��ִ�б�������
@echo.
@echo �˴�����ִ�е������У�
@echo easyjweb war							���������ǰ���̳�һ��war��
@echo easyjweb run							������Mavenֱ�����б�����
@echo easyjweb crud org.easyjf.domain.Example				��������һ�����������������Ӧ��
@echo easyjweb crud  org.easyjf.domain.Example ../src/main/java/org/easyjf/domain/Example.java	��������һ��JAVAԴ�ļ��������������Ӧ��
@echo ��������crudֻ��crud�ļ������ʹ�÷�����������ּ���ǣ���һ��JAVA�࣬�������JAVA��������Ӧ�������ļ���

:eof
@rem pause

if "%OS%"=="Windows_NT" ENDLOCAL
