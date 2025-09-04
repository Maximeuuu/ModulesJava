@echo off
setlocal enabledelayedexpansion

REM Racine du projet
cd /d "%~dp0"

REM Création du fichier JAR
set "nom_jar=FlexiLog.jar"
cd /d bin
jar cf ..\!nom_jar! .
cd ..

echo JAR genere : !nom_jar!
