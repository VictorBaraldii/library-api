# Library API

API REST para gerenciamento de livros, autores e usuários, desenvolvida com Spring Boot, autenticação JWT e OAuth2.

---

## Tecnologias

- Java 17
- Spring Boot
- Spring Security
- OAuth2 Authorization Server
- JWT
- Spring Data JPA
- Specification Pattern
- Maven
- Docker

---

## Arquitetura

O projeto segue arquitetura em camadas:

- Controller
- Service
- Repository
- DTO + Mapper
- Validator
- Exception Handler Global
- Security Layer

---

## Segurança

A API utiliza:

- OAuth2 Authorization Server
- JWT Token
- Custom Authentication Provider
- Custom UserDetailsService

---

## Como rodar o projeto

### 1️Clonar

```bash
git clone https://github.com/VictorBaraldii/library-api.git
cd library-api
