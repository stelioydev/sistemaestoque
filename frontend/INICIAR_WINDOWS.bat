@echo off
chcp 65001 >nul
title StockOS
cls
echo =====================================
echo          StockOS - Frontend
echo =====================================
echo.
if not exist node_modules (
  echo Instalando dependencias pela primeira vez...
  call npm install
  if errorlevel 1 (
    echo.
    echo Erro ao instalar dependencias. Verifique se o Node.js esta instalado.
    pause
    exit /b 1
  )
)
echo.
echo Iniciando StockOS...
echo O navegador pode ser aberto em http://localhost:5173
call npm run dev
pause
