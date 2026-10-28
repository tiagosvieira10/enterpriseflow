# EnterpriseFlow

EnterpriseFlow é um sistema de gestão comercial desenvolvido como projeto de portfólio para praticar Java, Spring Boot, APIs REST e persistência relacional. O objetivo é evoluí-lo de forma incremental, mantendo as regras de negócio organizadas e o banco de dados versionado.

## Tecnologias

- Java 25 e Spring Boot 4
- Spring Web MVC, Spring Data JPA e Hibernate
- PostgreSQL 17 e Flyway
- Maven Wrapper e Docker Compose

## Funcionalidades atuais

- Cadastro de produtos pela API REST
- Listagem de produtos cadastrados
- Validação de nome e preço
- Persistência no PostgreSQL, com migrações gerenciadas pelo Flyway

## Estrutura do módulo Produto

```text
produto/
├── api/                    # Controller e modelos de entrada/saída da API
├── application/            # Casos de uso e coordenação das operações
├── domain/                 # Regras de negócio do produto
└── infrastructure/
    └── persistence/        # Entidade JPA e repositório
```


## Próximos passos

O projeto está em desenvolvimento. A evolução planejada inclui testes automatizados, melhoria da separação entre domínio e persistência, consulta e manutenção de produtos, clientes, movimentações de estoque, vendas, autenticação e funcionalidades financeiras.
