@echo off
echo Limpando pasta bin...
rmdir /s /q bin
mkdir bin

echo Compilando o projeto...
javac -d bin src/br/biblioteca/*.java src/br/biblioteca/command/*.java src/br/biblioteca/console/*.java src/br/biblioteca/repositorio/*.java src/br/biblioteca/entidade/*.java

if %errorlevel% neq 0 (
    echo Erro na compilação.
) else (
    echo Compilação concluída com sucesso.
)

pause