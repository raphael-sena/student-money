# Sistema de Moeda Estudantil

Sistema desenvolvido para a Disciplina de Laboratório de Desenvolvimento de Software, lecionada pelo Professor João Paulo Aramuni.

## Visão Geral

O Sistema de Moeda Estudantil é uma aplicação distribuída que permite a gestão de uma moeda virtual para uso dentro do ambiente acadêmico. O sistema é composto por vários microsserviços e uma aplicação frontend.

## Arquitetura

O sistema é composto pelos seguintes componentes:

### Backend (Microsserviços)

1. **Control Service** (`code/backend/control-service`)
   - Serviço central de controle
   - Gerencia as regras de negócio principais
   - Porta: 8080

2. **User Service** (`code/backend/user-service`)
   - Gerenciamento de usuários
   - Autenticação e autorização
   - Porta: 8081

3. **Transaction Service** (`code/backend/transaction-service`)
   - Gerenciamento de transações
   - Processamento de pagamentos
   - Porta: 8082

4. **Notification Service** (`code/backend/notification-service`)
   - Envio de notificações
   - Suporte a múltiplos canais (email, WhatsApp, SMS)
   - Porta: 8083

5. **StudentMoney Gateway** (`code/backend/studentmoney-gateway`)
   - API Gateway
   - Roteamento de requisições
   - Porta: 8084

### Frontend

- Aplicação Angular (`code/frontend-app`)
- Interface moderna e responsiva
- Porta: 4200

## Pré-requisitos

- Java 17 ou superior
- Node.js 18 ou superior
- Docker e Docker Compose
- Maven
- Angular CLI

## Configuração do Ambiente

1. Clone o repositório:
```bash
git clone [https://github.com/raphael-sena/student-money.git]
cd sistema-moeda-estudantil
```

2. Configure as variáveis de ambiente:
   - Copie o arquivo `.env.example` para `.env`
   - Preencha as variáveis necessárias

3. Inicie os serviços com Docker Compose:
```bash
cd code/backend
docker-compose up -d
```

## Executando o Projeto

### Backend

1. Inicie o RabbitMQ e outros serviços necessários:
```bash
cd code/backend
docker-compose up -d
```

2. Execute cada microsserviço:
```bash
# Control Service
cd control-service
mvn spring-boot:run

# User Service
cd ../user-service
mvn spring-boot:run

# Transaction Service
cd ../transaction-service
mvn spring-boot:run

# Notification Service
cd ../notification-service
mvn spring-boot:run

# Gateway
cd ../studentmoney-gateway
mvn spring-boot:run
```

### Frontend

1. Instale as dependências:
```bash
cd code/frontend-app
npm install
```

2. Execute a aplicação:
```bash
ng serve
```

## Estrutura de Diretórios

```
sistema-moeda-estudantil/
├── code/
│   ├── backend/
│   │   ├── control-service/
│   │   ├── user-service/
│   │   ├── transaction-service/
│   │   ├── notification-service/
│   │   ├── studentmoney-gateway/
│   │   └── docker-compose.yml
│   └── frontend-app/
│       ├── src/
│       ├── public/
│       └── ...
└── docs/
    ├── diagramas/
    └── releases/
```

## Documentação

A documentação do projeto está organizada no diretório `docs/`:

- `diagramas/`: Contém diagramas de arquitetura, comunicação e casos de uso
- `releases/`: Documentação de releases e versões

## Comunicação entre Serviços

O sistema utiliza mensageria (RabbitMQ) para comunicação assíncrona entre os serviços. Principais filas:

- `transaction-notification-queue`: Notificações de transações
- Outras filas específicas para cada tipo de comunicação

## Contribuição

1. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
2. Faça commit das suas alterações (`git commit -m 'Adiciona nova feature'`)
3. Faça push para a branch (`git push origin feature/nova-feature`)
4. Abra um Pull Request

