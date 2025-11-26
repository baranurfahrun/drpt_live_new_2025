@echo off
echo ============================================
echo Compiling JRXML Report using Jaspersoft Studio
echo ============================================
echo.

set JSS_HOME=C:\Program Files\TIBCO\Jaspersoft Studio-6.8.0
set ECLIPSE_EXE="%JSS_HOME%\eclipsec.exe"
set JRXML_FILE=%~dp0report\rptDokumentasiKonsultasiMedik.jrxml
set OUTPUT_DIR=%~dp0report

echo Input:  %JRXML_FILE%
echo Output: %OUTPUT_DIR%
echo.

:: Check if JRXML file exists
if not exist "%JRXML_FILE%" (
    echo ERROR: JRXML file not found at %JRXML_FILE%
    pause
    exit /b 1
)

:: Backup old jasper file
if exist "%OUTPUT_DIR%\rptDokumentasiKonsultasiMedik.jasper" (
    echo Backing up old JASPER file...
    copy /Y "%OUTPUT_DIR%\rptDokumentasiKonsultasiMedik.jasper" "%OUTPUT_DIR%\rptDokumentasiKonsultasiMedik.jasper.bak"
    if %ERRORLEVEL% EQU 0 (
        echo Backup created: rptDokumentasiKonsultasiMedik.jasper.bak
    )
    echo.
)

:: Try Eclipse command line compilation
if exist %ECLIPSE_EXE% (
    echo Attempting command-line compilation...
    echo This may take a moment...
    echo.

    %ECLIPSE_EXE% -nosplash -data "%TEMP%\jaspersoft_workspace" -application net.sf.jasperreports.jrcompiler "%JRXML_FILE%" -verbose

    if %ERRORLEVEL% EQU 0 (
        echo.
        echo ============================================
        echo SUCCESS! Report compiled successfully!
        echo ============================================
        if exist "%OUTPUT_DIR%\rptDokumentasiKonsultasiMedik.jasper" (
            dir "%OUTPUT_DIR%\rptDokumentasiKonsultasiMedik.jasper"
        )
    ) else (
        echo.
        echo Command-line compilation not available.
        goto MANUAL_COMPILE
    )
) else (
    echo Eclipse executable not found.
    goto MANUAL_COMPILE
)

goto END

:MANUAL_COMPILE
echo.
echo ============================================
echo MANUAL COMPILATION REQUIRED
echo ============================================
echo.
echo Please follow these steps:
echo 1. Open Jaspersoft Studio
echo 2. File -^> Open -^> Browse to:
echo    %JRXML_FILE%
echo 3. Right-click on the file in Project Explorer
echo 4. Select "Compile Report"
echo 5. The .jasper file will be created in the same folder
echo.
echo Press any key to open Jaspersoft Studio...
pause >nul
start "" "C:\Program Files\TIBCO\Jaspersoft Studio-6.8.0\Jaspersoft Studio.exe"

:END
echo.
echo Done!
pause
