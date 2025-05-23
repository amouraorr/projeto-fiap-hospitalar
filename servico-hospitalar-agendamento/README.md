# projeto-fiap-hospitalar-agendamento

# Serviço de Agendamento Hospitalar - Backend

## Introdução

Este projeto faz parte de um desafio técnico para desenvolver um sistema de agendamento de consultas médicas em um ambiente hospitalar. O objetivo é criar uma solução backend robusta que permita o agendamento eficaz de consultas, gerenciamento do histórico de pacientes e envio de lembretes automáticos, garantindo a presença dos pacientes nas consultas.

## Objetivo do Projeto

O principal objetivo deste projeto é desenvolver um sistema escalável e seguro que atenda às necessidades de médicos, enfermeiros e pacientes, com acesso controlado e funcionalidades específicas para cada perfil. O sistema utiliza comunicação assíncrona através do Kafka para garantir a eficiência no envio de notificações e no gerenciamento do histórico de consultas.

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
projeto-fiap-hospitalar/
│
├── src/
│ └── main/
│   ├── java/
│   │ └── com.fiap.hospitalar.agendamento
│   │   ├── config/ : Configurações do sistema, incluindo segurança e Swagger.
│   │   ├── controller/ : Camada de controle que expõe APIs REST.
│   │   ├── dto/ : Objetos de transferência de dados (DTOs) para requisições e respostas.
│   │   ├── mapper/ : Mapeamento entre entidades e DTOs.
│   │   ├── message/ : Configuração e implementação do produtor Kafka.
│   │   ├── model/ : Classes que representam as entidades do sistema.
│   │   ├── repository/ : Interfaces para acesso ao banco de dados.
│   │   └── service/ : Lógica de negócios do sistema.
│   └── resources/
│       
├── pom.xml
├── README.md
├── Dockefile
├── docker-compose.yml
├── postman-collections/
└── ...

## Segurança

A segurança do sistema é tratada com o uso do Spring Security, que fornece autenticação e autorização robustas. As principais medidas de segurança implementadas incluem:

- **Autenticação**: O sistema pode ser configurado para exigir credenciais válidas (login e senha) para acessar os endpoints.
- **Proteção contra Ataques Comuns**: O sistema é configurado para proteger contra ataques comuns, como CSRF (Cross-Site Request Forgery) e XSS (Cross-Site Scripting), através de cabeçalhos de segurança e validação de entrada.

## Visão Geral do Projeto

Este projeto é um backend desenvolvido com o framework Spring Boot, utilizando PostgreSQL como banco de dados. O sistema é projetado para gerenciar agendamentos de consultas médicas, permitindo operações de criação, atualização, exclusão e recuperação de consultas.

## Arquitetura

A arquitetura do projeto segue o padrão MVC (Model-View-Controller) e é organizada em pacotes que separam as responsabilidades:

- **Model**: Contém as classes que representam as entidades do sistema, como `MedicalAppointment`.
- **Controller**: As classes de controlador (`MedicalAppointmentController`) gerenciam as requisições e interagem com os serviços.
- **Service**: Contém a lógica de negócios relacionada ao agendamento de consultas.
- **Repository**: Interfaces que definem métodos para interagir com o banco de dados.

## Princípios de Design e Padrões de Projeto

### Princípios de Design

1. **Single Responsibility Principle (SRP)**:
   - Cada classe deve ter uma única responsabilidade. Por exemplo, a classe `MedicalAppointmentService` é responsável apenas pela lógica de negócios relacionada ao agendamento de consultas, enquanto o `MedicalAppointmentController` lida com as requisições HTTP.

2. **Open/Closed Principle (OCP)**:
   - As classes devem estar abertas para extensão, mas fechadas para modificação. Isso significa que você pode adicionar novas funcionalidades (como novos tipos de notificações) sem alterar o código existente, utilizando interfaces e classes concretas.

3. **Liskov Substitution Principle (LSP)**:
   - As subclasses devem ser substituíveis por suas classes base. Por exemplo, se você tiver uma classe base `NotificationService` e subclasses como `EmailNotificationService` e `SmsNotificationService`, você deve ser capaz de usar qualquer uma dessas subclasses no lugar da classe base sem alterar o comportamento do sistema.

4. **Interface Segregation Principle (ISP)**:
   - É melhor ter várias interfaces específicas do que uma única interface geral. Por exemplo, em vez de ter uma interface `NotificationService` que inclui métodos para enviar e-mails, SMS e notificações push, você pode ter interfaces separadas como `EmailService`, `SmsService`, etc.

5. **Dependency Inversion Principle (DIP)**:
   - As classes de alto nível não devem depender de classes de baixo nível, mas ambas devem depender de abstrações. Isso é alcançado através da injeção de dependência, onde as classes dependem de interfaces em vez de implementações concretas.

### Padrões de Projeto

1. **Padrão MVC (Model-View-Controller)**:
   - A arquitetura do projeto segue o padrão MVC, onde:
     - **Model**: Representado pelas classes de modelo (`MedicalAppointment`).
     - **Controller**: As classes de controlador (`MedicalAppointmentController`) gerenciam as requisições e interagem com os serviços.
     - **View**: Embora não exista uma camada de visualização tradicional, as respostas JSON podem ser vistas como uma forma de "view".

2. **Padrão Repository**:
   - O padrão Repository é utilizado para abstrair a lógica de acesso a dados. As interfaces de repositório (`MedicalAppointmentRepository`) permitem que você interaja com o banco de dados sem expor detalhes da implementação.

3. **Padrão Service**:
   - O padrão Service é utilizado para encapsular a lógica de negócios. As classes de serviço (`MedicalAppointmentService`) contêm métodos que implementam a lógica necessária para manipular os dados e interagir com os repositórios.

## Interação entre as Partes do Sistema

1. **Requisição do Cliente**: O cliente faz uma requisição HTTP para um endpoint específico (por exemplo, `/appointments`).
2. **Controller**: O `MedicalAppointmentController` recebe a requisição e chama o método apropriado no `MedicalAppointmentService`.
3. **Service**: O `MedicalAppointmentService` executa a lógica de negócios necessária, como agendar uma nova consulta.
4. **Repository**: O `MedicalAppointmentService` interage com o `MedicalAppointmentRepository` para realizar operações no banco de dados.
5. **Comunicação Assíncrona**: Após salvar a consulta, uma mensagem é enviada para o Kafka, que é consumida por outros serviços (como o serviço de notificações).

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
   ```
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

### Passos para Acessar o GraphiQL

1. Certifique-se de que o servidor da aplicação está em execução.
2. Abra o seu navegador de internet.
3. Acesse o endereço: `http://localhost:8081/graphiql.html`.
4. A interface do GraphiQL será exibida.
5. Utilize o painel para realizar consultas (queries) e mutações (mutations) na API GraphQL do serviço de histórico.


## Link para a Collection do Postman

[Baixe a Collection do Postman aqui]// todo revisar os caminhos
https://github.com/amouraorr/projeto-fiap-hospitalar/tree/main/postman-collections/postman_collection.json

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
- [Kafka Documentation](https://kafka.apache.org/documentation/)
- [MapStruct Documentation](https://mapstruct.org/documentation/stable/reference/html/)
- [Lombok Documentation](https://projectlombok.org/)

## Conclusão

A aplicação incorpora vários princípios de design e padrões de projeto que promovem a manutenibilidade, extensibilidade e clareza do código. Esses princípios e padrões ajudam a garantir que a aplicação possa evoluir ao longo do tempo sem se tornar difícil de entender ou modificar.
