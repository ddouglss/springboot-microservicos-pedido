
# 📦 Microsserviços de Pedidos e Processamento

Este projeto é uma arquitetura de microsserviços composta por dois serviços independentes que se comunicam via RabbitMQ, utilizando PostgreSQL como banco de dados.

- Pedido Service (Porta 8081) → Responsável por cadastrar pedidos e publicá-los na fila.

- Processamento Service (Porta 8082) → Responsável por consumir os pedidos enviados para a fila e processá-los.


## 🚀 Tecnologias Utilizadas

- Java 21

- Spring Boot 3.5.4

- Spring AMQP

- Spring Data JPA

- PostgreSQL

- RabbitMQ (CloudAMQP)

- Maven

## ⚙️ Arquitetura

```
flowchart LR
    A[Cliente] -->|HTTP POST /v1/pedidos| B[Pedido Service]
    B -->|INSERT| DB1[(PostgreSQL Pedido)]
    B -->|Mensagem RabbitMQ| Q[Queue: exchange.processamento]
    Q --> C[Processamento Service]
    C -->|INSERT| DB2[(PostgreSQL Processamento)]

```
## 📂 Estrutura do Projeto

```
/microservices-project
 ├── pedido/              # Serviço de Pedido (Producer)
 │   ├── controller/       # Endpoints REST
 │   ├── service/          # Regras de negócio
 │   ├── model/            # Entidades JPA
 │   ├── repository/       # Repositórios JPA
 │   ├── dto/              # DTOs
 │   └── mapper/           # Conversores DTO ↔ Entidade
 │
 ├── src/main/resources/
 │    ├── application.properties
 │
 └── pom.xml              # Configurações do Maven
```
## Clone o repositório:

```
git clone https://github.com/seu-usuario/ms-processamento.git
cd ms-processamento
```


#### Compile e rode o projeto:

```
mvn spring-boot:run
```

#### O serviço estará disponível em:

```
http://localhost:8082
```
## 🔄 Fluxo de Mensagens

- Cliente envia um pedido via API REST para o Pedido Service.

- O pedido é salvo no PostgreSQL e publicado na fila exchange.processamento.

- O Processamento Service consome essa mensagem.

- O processamento pode ser logado, persistido ou gerar novos eventos.
## 🧪 Testes

O projeto inclui dependências para testes com JUnit, TestNG e Spring RabbitMQ Test.
Para rodar:

```
mvn test
```
