# Sistema de Biblioteca - Engenharia de Software I

Este projeto simula um sistema de biblioteca para cadastro de livros, usuários, reservas e empréstimos, utilizando padrões de projeto como Singleton e Command, desenvolvido para a disciplina de Engenharia de Software I.

## Pré-requisitos

- Java JDK 8 ou superior instalado
- Terminal ou PowerShell no Windows

## Estrutura do projeto

- `src/`: Contém os arquivos `.java` organizados por pacotes:
  - `entidade/`
  - `repositorio/`
  - `command/`
  - `console/`
- `bin/`: Gerado automaticamente após compilação, contém os `.class`.
- `build.bat`: Script para compilar e rodar o projeto automaticamente.

## Como compilar e executar

1️⃣ Abra o PowerShell ou terminal na **pasta raiz do projeto** (onde está `build.bat`).

2️⃣ Execute o comando:
`./build.bat`

Este comando:
✅ Limpa a pasta `bin/`  
✅ Compila todo o projeto  

Para executar o sistema utilize o comando:
`java -cp bin br.biblioteca.Main`

## Comandos disponíveis

Digite os comandos abaixo no console do sistema:

- `emp <codigo_usuario> <codigo_livro>` : Realizar empréstimo de livro.
- `dev <codigo_usuario> <codigo_livro>` : Devolver livro.
- `liv <codigo_livro>` : Consultar informações de um livro.
- `usu <codigo_usuario>` : Consultar informações de um usuário.
- `res <codigo_usuario> <codigo_livro>` : Reservar livro.
- `ntf <codigo_usuario>` : Consultar notificações do usuário.
- `sair` : Encerrar o sistema.
