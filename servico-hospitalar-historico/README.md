Claro! Vamos criar um README para o serviço de histórico, seguindo a estrutura que você forneceu e adaptando para o contexto do seu projeto. Aqui está uma versão ajustada:

# Serviço de Histórico - Backend

## Introdução

Este projeto faz parte de um desafio técnico para desenvolver um sistema de gerenciamento do histórico de consultas médicas em um ambiente hospitalar. O objetivo é criar uma solução backend robusta que permita o armazenamento e recuperação do histórico de pacientes, garantindo que as informações estejam disponíveis para médicos, enfermeiros e pacientes.

## Objetivo do Projeto

O principal objetivo deste projeto é implementar um serviço que escute mensagens de agendamentos de consultas via Kafka, armazene essas informações no banco de dados e disponibilize os dados através de uma API GraphQL. O sistema deve ser seguro, escalável e utilizar boas práticas de autenticação e autorização.

## Requisitos do Sistema

Para executar este projeto, você precisará dos seguintes requisitos de sistema:

- **Sistema Operacional**: Windows, macOS ou Linux
- **Memória RAM**: Pelo menos 4 GB recomendados
- **Espaço em Disco**: Pelo menos 500 MB de espaço livre
- **Software**:
    - Docker e Docker Compose
    - Java JDK 11 ou superior
    - Maven 3.6 ou superior
    - Git

## Estrutura do Projeto

A estrutura do projeto é organizada da seguinte forma:
```plaintext
projeto-fiap-hospitalar-historico/
│
├── src/
│ └── main/
│   ├── java/
│   │ └── com.fiap.hospitalar.historico
│   │   ├── config/ : Configurações do sistema, incluindo segurança e CORS.
│   │   ├── controller/ : Camada de controle que expõe a API GraphQL.
│   │   ├── dto/ : Objetos de transferência de dados (DTOs) para requisições e respostas.
│   │   ├── mapper/ : Mapeamento entre entidades e DTOs.
│   │   ├── message/ : Configuração e implementação do consumidor Kafka.
│   │   ├── model/ : Classes que representam as entidades do sistema.
│   │   ├── repository/ : Interfaces para acesso ao banco de dados.
│   │   └── service/ : Lógica de negócios do sistema.
│   └── resources/
│       ├── graphql/ : Esquemas GraphQL utilizados pela aplicação.
│       │   └── fullSchema.graphqls : Definição do esquema GraphQL completo.
│       └── static/ : Arquivos estáticos utilizados pela aplicação.
│           ├── graphiql.css : Estilos para a interface do GraphiQL.
│           ├── graphiql.min.js : Script minificado para a interface do GraphiQL.
│           ├── react.production.min.js : Biblioteca React minificada.
│           ├── react-dom.production.min.js : Biblioteca React DOM minificada.
│           └── graphiql.html : Página HTML para a interface do GraphiQL.
│       
├── pom.xml : Arquivo de configuração do Maven, contendo dependências e plugins.
├── README.md : Documentação do projeto, incluindo instruções e informações relevantes.
├── Dockerfile : Arquivo de configuração para a construção da imagem Docker da aplicação.
├── docker-compose.yml : Arquivo de configuração para orquestração de contêineres Docker.
├── postman-collections/ : Coleções do Postman para testes da API.
└── ...


## Segurança

A segurança do sistema é tratada com o uso do Spring Security, que fornece autenticação e autorização robustas. As principais medidas de segurança implementadas incluem:

- **Autenticação**: Os usuários devem fornecer credenciais válidas (login e senha) para acessar o sistema.
- **Proteção contra Ataques Comuns**: O sistema é configurado para proteger contra ataques comuns, como CSRF (Cross-Site Request Forgery) e XSS (Cross-Site Scripting), através de cabeçalhos de segurança e validação de entrada.

## Visão Geral do Projeto

Este projeto é um backend desenvolvido com o framework Spring Boot, utilizando PostgreSQL como banco de dados. O sistema é projetado para gerenciar o histórico de consultas médicas, permitindo operações de criação, recuperação e armazenamento de dados.

## Arquitetura

A arquitetura do projeto segue o padrão MVC (Model-View-Controller) e é organizada em pacotes que separam as responsabilidades:

- **Model**: Contém as classes que representam as entidades do sistema, como `History`.
- **Controller**: As classes de controlador (`GraphQLController`) gerenciam as requisições e interagem com os serviços.
- **Service**: Contém a lógica de negócios relacionada ao gerenciamento do histórico de consultas.
- **Repository**: Interfaces que definem métodos para interagir com o banco de dados.

## Utilização do GraphiQL

### O que é GraphiQL?
GraphiQL é uma ferramenta de interface gráfica que permite aos desenvolvedores interagir com APIs GraphQL de forma intuitiva. Com ela, é possível realizar consultas, mutações e explorar o esquema da API de maneira visual.

### Acessando o GraphiQL
Para acessar a interface do GraphiQL, inicie o servidor da aplicação e navegue até o seguinte endereço no seu navegador:

http://localhost:8081/graphiql

### Realizando Consultas
Na interface do GraphiQL, você pode realizar consultas para obter dados do histórico de consultas. Aqui estão alguns exemplos de consultas que podem ser realizadas:

#### 1. Consultar Todo o Histórico de Consultas
```graphql
query {
  getAllHistories {
    id
    paciente
    medico
    enfermeiro
    dataHora
  }
}

**Descrição:** Esta consulta retorna todos os registros do histórico de consultas, incluindo informações sobre o paciente, médico, enfermeiro e data/hora da consulta.

#### 2. Consultar Histórico de Consultas por Paciente
```graphql
query {
  getPatientHistory(paciente: "Nome do Paciente") {
    id
    medico
    enfermeiro
    dataHora
  }
}

**Descrição:** Esta consulta retorna o histórico de consultas para um paciente específico, permitindo que você veja todas as consultas realizadas por ele.

#### 3. Consultar Histórico de Consultas por Médico
```graphql
query {
  getHistoriesByMedico(medico: "Nome do Médico") {
    id
    paciente
    enfermeiro
    dataHora
  }
}

**Descrição:** Esta consulta retorna todas as consultas realizadas por um médico específico.

### Explorando o Esquema
A interface do GraphiQL também permite que você explore o esquema GraphQL da API. Você pode visualizar os tipos de dados disponíveis, as consultas e mutações que podem ser realizadas, e os campos que cada tipo contém. Isso facilita a compreensão da estrutura da API e ajuda na construção de consultas mais complexas.

### Conclusão
A utilização do GraphiQL proporciona uma maneira prática e eficiente de interagir com a API GraphQL do serviço de histórico, permitindo que desenvolvedores e usuários testem e explorem as funcionalidades disponíveis de forma intuitiva.

## Princípios de Design e Padrões de Projeto

### Princípios de Design

1. **Single Responsibility Principle (SRP)**:
    - Cada classe deve ter uma única responsabilidade. Por exemplo, a classe `HistoryService` é responsável apenas pela lógica de negócios relacionada ao histórico de consultas, enquanto o `GraphQLController` lida com as requisições HTTP.

2. **Open/Closed Principle (OCP)**:
    - As classes devem estar abertas para extensão, mas fechadas para modificação. Isso é alcançado através do uso de interfaces e classes que implementam essas interfaces.

3. **Dependency Inversion Principle (DIP)**:
    - As classes de alto nível não devem depender de classes de baixo nível, mas ambas devem depender de abstrações. Isso é alcançado através da injeção de dependência.

### Padrões de Projeto

1. **MVC (Model-View-Controller)**:
    - A estrutura segue o padrão MVC, onde:
    - **Model**: Representado pelas classes de modelo (`History`).
    - **Controller**: As classes de controlador (`GraphQLController`) gerenciam as requisições e interagem com os serviços.
    - **View**: Embora não exista uma camada de visualização tradicional, as respostas JSON podem ser vistas como uma forma de "view".

## Interação entre as Partes do Sistema

1. **Requisição do Cliente**: O cliente faz uma requisição HTTP para um endpoint específico (por exemplo, `/graphql`).
2. **Controller**: O `GraphQLController` recebe a requisição e chama o método apropriado no `HistoryService`.
3. **Service**: O `HistoryService` executa a lógica de negócios necessária, como armazenar ou recuperar o histórico de consultas.
4. **Repository**: O `HistoryService` interage com o `HistoryRepository` para realizar operações no banco de dados.
5. **Resposta ao Cliente**: Após processar a requisição, o `GraphQLController` retorna uma resposta ao cliente, que pode incluir dados ou mensagens de erro.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **PostgreSQL**: Sistema de gerenciamento de banco de dados relacional.
- **Kafka**: Sistema de mensageria para comunicação assíncrona entre serviços.
- **MapStruct**: Biblioteca para mapeamento de objetos.
- **Lombok**: Biblioteca para reduzir o boilerplate de código em classes Java.

## Pré-requisitos

Antes de executar o projeto, certifique-se de ter as seguintes ferramentas instaladas:

- **Docker**
- **Docker Compose**
- **Java JDK 11 ou superior**
- **Maven 3.6 ou superior**
- **Git**

## Como Executar o Projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/amouraorr/projeto-fiap-hospitalar.git
   ```
   *Obs: utilizar a branch 'main'.

### Passos para Criar o Pacote do Projeto

1. No terminal, navegue até o diretório do projeto.
2. Execute o comando do Maven para compilar e empacotar o projeto:
   ```bash
   mvn clean package -DskipTests
   ```

### Passos para Executar o Docker Compose

1. Certifique-se de que o Docker e o Docker Compose estejam instalados e rodando na sua máquina.
2. No terminal, navegue até o diretório onde está localizado o arquivo `docker-compose.yml`.
3. Execute o seguinte comando para iniciar os contêineres:
   ```bash
   docker compose up

4. A aplicação estará disponível em `http://localhost:8080` e o Swagger em `http://localhost:8080/swagger-ui/index.html#/`.
5. O banco de dados PostgreSQL estará rodando em `http://localhost:5432`.
6. A ferramenta Adminer estará disponível para visualização do banco de dados no endereço `http://localhost:8181`.

### Passos para Conectar no Banco de Dados com o Adminer

1. Acesse o endereço `http://localhost:8181`.
2. Em Sistema, escolha PostgreSQL.
3. Em Servidor, preencha o nome do serviço do Postgres do Docker Compose (postgres).
4. Em Usuário, preencha postgres.
5. Em Senha, preencha postgres.
6. Em Base de dados, preencha com postgres.
7. Clique em Entrar.

![Login Adminer](adminer.png)

## Link para a Collection do Postman

[Baixe a Collection do Postman aqui](https://github.com/amouraorr/projeto-fiap-hospitalar/blob/main/postman-collections/pos-fiap-2025-agendamento-hospitalar.postman_collection.json)

### Como Importar a Collection do Postman

1. Baixe o arquivo JSON da collection usando o link acima.
2. Abra o Postman.
3. Clique no ícone de importar (Import) no canto superior esquerdo.
4. Selecione "Upload Files".
5. Escolha o arquivo JSON baixado e clique em "Open".
6. A coleção será importada para o Postman e estará disponível para uso.

## Contribuição

### Contribuições são bem-vindas! Siga estas etapas para contribuir:

1. Faça um fork do repositório.
2. Crie uma nova branch (`git checkout -b feature/nova-funcionalidade`).
3. Faça suas alterações e commit (`git commit -m 'Adiciona nova funcionalidade'`).
4. Envie para o repositório remoto (`git push origin feature/nova-funcionalidade`).
5. Abra um Pull Request.

## Licença

O projeto é privado ou não está sob uma licença específica.

## Referências e Recursos

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [PostgreSQL Documentation](https://www.postgresql.org/docs/)
- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- [Lombok Documentation](https://projectlombok.org/)
- [Kafka Documentation](https://kafka.apache.org/documentation/)

## Conclusão

A aplicação incorpora vários princípios de design e padrões de projeto que promovem a manutenibilidade, extensibilidade e clareza do código. Esses princípios e padrões ajudam a garantir que a aplicação possa evoluir ao longo do tempo sem se tornar difícil de entender ou modificar.
