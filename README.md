# 📋 Booking API (PT - BR)

Uma API REST desenvolvida para gerenciar o ciclo de vida completo de reservas de hotéis e pousadas fictícios.
Com ela, é possível cadastrar clientes, quartos e reservas, além de registrar e acompanhar pagamentos utilizando a plataforma Stripe (com suporte ao método cartão de crédito).

Esta aplicação é exclusivamente backend e foi construída com foco em organização, modularidade e boas práticas do ecossistema Spring.

## 🚀 Tecnologias Utilizadas

- Java 21

- Spring Boot

    - Spring Web

    - Spring Data JPA

    - Validation

- PostgreSQL

- Hibernate / JPA

- Stripe API (pagamentos via cartão)

- Postman (para testes de requisições HTTP)

## ⚙️ Funcionalidades

A API implementa CRUDs completos e rotinas de ciclo de vida de reserva:

### 👤 Clientes

- Criar cliente

- Buscar cliente por ID ou listar todos

- Atualizar dados

- Excluir cliente

### 🏨 Quartos

- Cadastrar quarto

- tualizar informações

- Listar / pesquisar

- Excluir quarto

### 📅 Reservas

- Criar reserva vinculada a um cliente e a um quarto

- Consultar reservas

- Atualizar status da reserva

- Excluir reserva

### 💰 Pagamentos

(Por meio da Stripe API — cartão)

- Criar intenção de pagamento

- Consultar status

- Associar pagamento a uma reserva

- Atualizar status conforme retorno da Stripe

## 🛠️ Arquitetura do Projeto

O projeto está organizado da seguinte forma:

``` 
src/main/java
└── com.mkassianney.demo
     ├── Controllers      → Controladores HTTP (endpoints)
     ├── Model            → Entities e Enums
     ├── DTOs             → Objetos de transferência de dados
     ├── Service          → Camada de regras de negócio
     └── Repository       → Interfaces JPA para persistência

```

## 🛢️ Configuração do Banco de Dados
A aplicação utiliza banco PostgreSQL. Exemplo de configuração:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_api
spring.datasource.username=postgres
spring.datasource.password=senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

## 🔌 Integração com Stripe

```
stripe.api.key=YOUR_SECRET_KEY

```

## 🧪 Testes com Postman

Toda a API foi testada com o Postman para simular requisições HTTP:

- POST / PUT / DELETE / GET

- Envio de JSON

- Cenários de pagamento

- Validações

- Teste de fluxo completo: cliente → reserva → pagamento

## ▶️ Como Rodar o Projeto

1. Clone o repositório:

```
git clone https://github.com/seu-repositorio/hotel-api.git

```

2. Configure o banco e a chave Stripe no application.properties.
3. Execute:
```
mvn spring-boot:run

```

A API estará disponível em:
```
http://localhost:8080
```

## 📘 Objetivo do Projeto

Um projeto focado em:

- Estrutura limpa e modular

- Boas práticas com Spring Boot

- CRUDs completos

- Integração com API externa (Stripe)

- Fluxo realista de reservas e pagamentos

## 👩‍💻 Autor

Projeto desenvolvido por Maria Eduarda Kassianney ✨

# 📋 Booking API (EN - US)

A REST API developed to manage the complete lifecycle of fictional hotel and inn reservations.
With it, you can register clients, rooms, and reservations, as well as record and track payments using the Stripe platform (with support for PIX and credit card methods).

This application is exclusively backend and was built with a focus on organization, modularity, and best practices of the Spring ecosystem.

## 🚀 Technologies Used

- Java 21

- Spring Boot

    - Spring Web

    - Spring Data JPA

    - Validation

- PostgreSQL

- Hibernate / JPA

- Stripe API (payments via credit card)

- Postman (for testing HTTP requests)

## ⚙️ Features

The API implements complete CRUDs and reservation lifecycle routines:

### 👤 Clients

- Create client

- Fetch client by ID or list all

- Update data

- Delete client

### 🏨 Rooms

- Register room

- Update information

- List / search

- Delete room

### 📅 Reservations

- Create reservation linked to a client and a room

- Query reservations

- Update reservation status

- Delete reservation

### 💰 Payments

(Via Stripe API — credit card)

- Create payment intent

- Query status

- Associate payment with a reservation

- Update status based on Stripe response

## 🛠️ Project Architecture

The project is organized as follows:

``` 
src/main/java
└── com.mkassianney.demo
     ├── Controllers      → HTTP Controllers (endpoints)
     ├── Model            → Entities and Enums
     ├── DTOs             → Data Transfer Objects
     ├── Service          → Business logic layer
     └── Repository       → JPA interfaces for persistence

```

## 🛢️ Database Configuration
The application uses PostgreSQL database. Configuration example:

```
spring.datasource.url=jdbc:postgresql://localhost:5432/hotel_api
spring.datasource.username=postgres
spring.datasource.password=password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

## 🔌 Stripe Integration

```
stripe.api.key=YOUR_SECRET_KEY

```

## 🧪 Testing with Postman

The entire API was tested with Postman to simulate HTTP requests:

- POST / PUT / DELETE / GET

- JSON payload

- Payment scenarios

- Validations

- Complete flow test: client → reservation → payment

## ▶️ How to Run the Project

1. Clone the repository:

```
git clone https://github.com/seu-repositorio/hotel-api.git

```

2. Configure the database and Stripe key in application.properties.
3. Execute:
```
mvn spring-boot:run

```

The API will be available at:
```
http://localhost:8080
```

## 📘 Project Objective

A project focused on:

- Clean and modular structure

- Best practices with Spring Boot

- Complete CRUDs

- External API integration (Stripe)

- Realistic reservation and payment flow

## 👩‍💻 Author

Project developed by Maria Eduarda Kassianney ✨
