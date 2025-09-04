@echo off
setlocal enabledelayedexpansion

REM Racine du projet
cd /d "%~dp0"

REM Vide le fichier compile.list
type nul > compile.list

REM Parcourt tous les fichiers .java sous src et ajoute le chemin relatif
for /r src %%f in (*.java) do (
    set "full=%%f"
    set "rel=!full:*src\=src\!"
    echo !rel! >> compile.list
)

REM Compile avec java
javac @option.list @compile.list

if errorlevel 1 (
    echo [ERREUR] La compilation a echoue.
    exit /b 1
)

echo [OK] Compilation terminee.

REM Supprime l’ancienne doc
rmdir /s /q doc 2>nul

REM Cree la nouvelle doc
javadoc -d doc -encoding UTF-8 -charset UTF-8 -sourcepath src/main -subpackages fr.flexilog >nul 2>nul

if errorlevel 1 (
    echo [ERREUR] La generation de la Javadoc a echoue.
    exit /b 1
)

echo [OK] Javadoc generee dans le dossier 'doc\'

endlocal
