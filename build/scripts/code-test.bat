@if "%DEBUG%" == "" @echo off
@rem ##########################################################################
@rem
@rem  code-test startup script for Windows
@rem
@rem ##########################################################################

@rem Set local scope for the variables with windows NT shell
if "%OS%"=="Windows_NT" setlocal

set DIRNAME=%~dp0
if "%DIRNAME%" == "" set DIRNAME=.
set APP_BASE_NAME=%~n0
set APP_HOME=%DIRNAME%..

@rem Add default JVM options here. You can also use JAVA_OPTS and CODE_TEST_OPTS to pass JVM options to this script.
set DEFAULT_JVM_OPTS=

@rem Find java.exe
if defined JAVA_HOME goto findJavaFromJavaHome

set JAVA_EXE=java.exe
%JAVA_EXE% -version >NUL 2>&1
if "%ERRORLEVEL%" == "0" goto init

echo.
echo ERROR: JAVA_HOME is not set and no 'java' command could be found in your PATH.
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:findJavaFromJavaHome
set JAVA_HOME=%JAVA_HOME:"=%
set JAVA_EXE=%JAVA_HOME%/bin/java.exe

if exist "%JAVA_EXE%" goto init

echo.
echo ERROR: JAVA_HOME is set to an invalid directory: %JAVA_HOME%
echo.
echo Please set the JAVA_HOME variable in your environment to match the
echo location of your Java installation.

goto fail

:init
@rem Get command-line arguments, handling Windows variants

if not "%OS%" == "Windows_NT" goto win9xME_args

:win9xME_args
@rem Slurp the command line arguments.
set CMD_LINE_ARGS=
set _SKIP=2

:win9xME_args_slurp
if "x%~1" == "x" goto execute

set CMD_LINE_ARGS=%*

:execute
@rem Setup the command line

set CLASSPATH=%APP_HOME%\lib\code-test-1.0-SNAPSHOT.jar;%APP_HOME%\lib\spring-boot-starter-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-web-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-actuator-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-context-support-4.3.9.RELEASE.jar;%APP_HOME%\lib\jersey-client-2.25.1.jar;%APP_HOME%\lib\jersey-media-json-jackson-2.25.1.jar;%APP_HOME%\lib\guava-22.0.jar;%APP_HOME%\lib\logback-classic-1.2.3.jar;%APP_HOME%\lib\logback-access-1.2.3.jar;%APP_HOME%\lib\lombok-1.16.16.jar;%APP_HOME%\lib\springfox-swagger2-2.7.0.jar;%APP_HOME%\lib\springfox-swagger-ui-2.7.0.jar;%APP_HOME%\lib\jackson-jaxrs-json-provider-2.8.8.jar;%APP_HOME%\lib\commons-lang3-3.6.jar;%APP_HOME%\lib\commons-io-2.5.jar;%APP_HOME%\lib\spring-boot-starter-aop-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-boot-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-boot-autoconfigure-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-boot-starter-logging-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-core-4.3.16.RELEASE.jar;%APP_HOME%\lib\snakeyaml-1.17.jar;%APP_HOME%\lib\spring-boot-starter-tomcat-1.5.12.RELEASE.jar;%APP_HOME%\lib\hibernate-validator-5.3.6.Final.jar;%APP_HOME%\lib\jackson-databind-2.8.11.1.jar;%APP_HOME%\lib\spring-web-4.3.16.RELEASE.jar;%APP_HOME%\lib\spring-webmvc-4.3.16.RELEASE.jar;%APP_HOME%\lib\spring-boot-actuator-1.5.12.RELEASE.jar;%APP_HOME%\lib\spring-beans-4.3.16.RELEASE.jar;%APP_HOME%\lib\spring-context-4.3.16.RELEASE.jar;%APP_HOME%\lib\javax.ws.rs-api-2.0.1.jar;%APP_HOME%\lib\jersey-common-2.25.1.jar;%APP_HOME%\lib\hk2-api-2.5.0-b32.jar;%APP_HOME%\lib\javax.inject-2.5.0-b32.jar;%APP_HOME%\lib\hk2-locator-2.5.0-b32.jar;%APP_HOME%\lib\jersey-entity-filtering-2.25.1.jar;%APP_HOME%\lib\jackson-jaxrs-base-2.8.11.jar;%APP_HOME%\lib\jackson-annotations-2.8.0.jar;%APP_HOME%\lib\jsr305-1.3.9.jar;%APP_HOME%\lib\error_prone_annotations-2.0.18.jar;%APP_HOME%\lib\j2objc-annotations-1.1.jar;%APP_HOME%\lib\animal-sniffer-annotations-1.14.jar;%APP_HOME%\lib\logback-core-1.1.11.jar;%APP_HOME%\lib\slf4j-api-1.7.25.jar;%APP_HOME%\lib\swagger-annotations-1.5.13.jar;%APP_HOME%\lib\swagger-models-1.5.13.jar;%APP_HOME%\lib\springfox-spi-2.7.0.jar;%APP_HOME%\lib\springfox-schema-2.7.0.jar;%APP_HOME%\lib\springfox-swagger-common-2.7.0.jar;%APP_HOME%\lib\springfox-spring-web-2.7.0.jar;%APP_HOME%\lib\classmate-1.3.4.jar;%APP_HOME%\lib\spring-plugin-core-1.2.0.RELEASE.jar;%APP_HOME%\lib\spring-plugin-metadata-1.2.0.RELEASE.jar;%APP_HOME%\lib\mapstruct-1.1.0.Final.jar;%APP_HOME%\lib\jackson-core-2.8.11.jar;%APP_HOME%\lib\jackson-module-jaxb-annotations-2.8.11.jar;%APP_HOME%\lib\spring-aop-4.3.16.RELEASE.jar;%APP_HOME%\lib\aspectjweaver-1.8.13.jar;%APP_HOME%\lib\jcl-over-slf4j-1.7.25.jar;%APP_HOME%\lib\jul-to-slf4j-1.7.25.jar;%APP_HOME%\lib\log4j-over-slf4j-1.7.25.jar;%APP_HOME%\lib\tomcat-embed-core-8.5.29.jar;%APP_HOME%\lib\tomcat-embed-el-8.5.29.jar;%APP_HOME%\lib\tomcat-embed-websocket-8.5.29.jar;%APP_HOME%\lib\validation-api-1.1.0.Final.jar;%APP_HOME%\lib\jboss-logging-3.3.2.Final.jar;%APP_HOME%\lib\spring-expression-4.3.16.RELEASE.jar;%APP_HOME%\lib\javax.annotation-api-1.2.jar;%APP_HOME%\lib\jersey-guava-2.25.1.jar;%APP_HOME%\lib\osgi-resource-locator-1.0.1.jar;%APP_HOME%\lib\hk2-utils-2.5.0-b32.jar;%APP_HOME%\lib\aopalliance-repackaged-2.5.0-b32.jar;%APP_HOME%\lib\javassist-3.21.0-GA.jar;%APP_HOME%\lib\springfox-core-2.7.0.jar;%APP_HOME%\lib\reflections-0.9.11.jar;%APP_HOME%\lib\tomcat-annotations-api-8.5.29.jar;%APP_HOME%\lib\byte-buddy-1.6.14.jar

@rem Execute code-test
"%JAVA_EXE%" %DEFAULT_JVM_OPTS% %JAVA_OPTS% %CODE_TEST_OPTS%  -classpath "%CLASSPATH%" uk.co.ts.CodeTestApplication %CMD_LINE_ARGS%

:end
@rem End local scope for the variables with windows NT shell
if "%ERRORLEVEL%"=="0" goto mainEnd

:fail
rem Set variable CODE_TEST_EXIT_CONSOLE if you need the _script_ return code instead of
rem the _cmd.exe /c_ return code!
if  not "" == "%CODE_TEST_EXIT_CONSOLE%" exit 1
exit /b 1

:mainEnd
if "%OS%"=="Windows_NT" endlocal

:omega
