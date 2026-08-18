# Sistema Bancário

Sistema bancário educacional desenvolvido em Java para console.

## Objetivo

Simular operações básicas de um banco utilizando Programação Orientada a Objetos (POO), arquitetura MVC, coleções `Map` e `Set`/estruturas de coleção, persistência em arquivos e separação de responsabilidades.

## Funcionalidades

- Cadastro de clientes e contas
- Validação de CPF
- Prevenção de CPF e usuário duplicados
- Login com senha armazenada como hash SHA-256
- Persistência das informações em arquivo `.dat`
- Depósito
- Saque
- Transferência entre contas
- Empréstimo
- Sistema de crédito
- Investimentos
- Extrato
- Geração de relatório `.txt`
- Testes básicos de login e operações
- Menu de console

## Estrutura

```text
SistemaBancario/
├── src/
│   └── main/
│      └── java/
│          └── bancario/
│              ├── Main.java
│              ├── controller/
│              ├── model/
│              ├── util/
│              └── view/
│   
│       
│       
├── dados_bancarios.dat
├── relatorio_bancario.txt
└── README.md
```

A organização foi inspirada na estrutura do projeto Gerenciador de Inter-séries, mantendo a separação entre `controller`, `model`, `view`, `util` e `Main`.

## MVC

- **Model:** representa clientes, contas, transações, empréstimos, crédito, investimentos e extrato.
- **Controller:** concentra as regras de negócio e coordena as operações.
- **View:** apresenta os menus e recebe os dados do usuário.
- **Util:** contém persistência em arquivos e validações auxiliares.

## Persistência

O sistema utiliza:

- `dados_bancarios.dat` para persistência binária dos clientes, contas e movimentações.
- `relatorio_bancario.txt` para o relatório final.

Os arquivos são criados automaticamente na pasta em que o programa é executado.

## Compilação

Na raiz do projeto:

```bash
javac -d out $(find src/main/java -name "*.java")
```

No Windows PowerShell, uma alternativa é:

```powershell
Get-ChildItem -Recurse src/main/java/*.java | ForEach-Object { $_.FullName } | Set-Content fontes.txt
javac -d out @fontes.txt
```

## Execução

```bash
java -cp out bancario.Main
```

## Testes

Teste de login:

```bash
javac -d out -cp out src/test/java/bancario/LoginTest.java
java -cp out bancario.LoginTest
```

Teste das operações:

```bash
javac -d out -cp out src/test/java/bancario/OperacoesTest.java
java -cp out bancario.OperacoesTest
```

## Exemplo de fluxo

1. Escolher `Cadastrar`.
2. Informar nome, CPF, usuário e senha.
3. Fazer login.
4. Depositar dinheiro.
5. Realizar saque ou transferência.
6. Utilizar crédito ou solicitar empréstimo.
7. Criar investimento.
8. Consultar o extrato.
9. Gerar o relatório.

## POO utilizada

- **Herança:** `Cliente` herda de `Pessoa`.
- **Classe abstrata:** `Pessoa`.
- **Interface:** `OperacaoBancaria`.
- **Polimorfismo:** `ContaBancaria` implementa as operações definidas pela interface.
- **Encapsulamento:** atributos privados com métodos públicos controlados.
- **Coleções:** `Map` para clientes e listas para movimentações e operações.

## Observação

Este é um sistema educacional e não deve ser utilizado como software bancário real. As regras de crédito, empréstimo e investimento são simplificadas para fins didáticos.
