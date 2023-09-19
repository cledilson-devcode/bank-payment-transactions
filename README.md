# Projeto de Transações Bancárias

Este é um projeto de exemplo para demonstrar uma aplicação de transações bancárias utilizando Java 17, Spring MVC, JPA (Java Persistence API) e o banco de dados H2.

## Visão Geral

Este projeto simula um sistema de transações bancárias com os seguintes recursos:

- Cadastro de Usuários, Clientes e Lojas.
- Envio e recebimento de transações entre Usuários e Clientes.
- Recebimento de transações pelas Lojas.

## Pré-requisitos

- Java 17 instalado.
- Maven para gerenciamento de dependências.
- Banco de dados H2 (configurado no arquivo `application.properties`).

## Configuração

  1. **Clone o repositório:**

    ```bash
    git clone https://github.com/seu-usuario/seu-projeto.git
    cd seu-projeto

  2. **Execute a aplicação:**

      ```bash
      mvn spring-boot:run

3. **Acesse a API no navegador ou em uma ferramenta como o Postman:**

http://localhost:8080



## Endpoints da API
API oferece os seguintes endpoints:

- '/users' : Criar novo usuário.
  
  Exemplos de Uso
  Para criar um novo usuário:
    ```bash
    POST /users
    {
      "firstName": "Daniel",
      "lastName": "Araujo",
      "document": "11111111111111",
      "email": "daniel@gmail.com",
      "userType": "MERCHANT",
      "password": "senha",
      "balance": 50
    }

- '/transactions' : Criar novo transação.
  
  Exemplos de Uso
  Para criar uma nova transação:
    ```bash
    POST /transactions
    {
      "senderId": 2,
      "receiverId": 1,
      "value": 10
    }



## Contribuição
Sinta-se à vontade para contribuir com melhorias para este projeto. Você pode abrir problemas (issues) ou enviar solicitações de pull (pull requests).
