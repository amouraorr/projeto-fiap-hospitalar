# projeto-fiap-hospitalar-notificacoes

# Serviço de Notificações - Backend

## Introdução

Este projeto faz parte de um desafio técnico para desenvolver um sistema de notificações para um ambiente hospitalar. O objetivo é criar uma solução backend robusta que permita o envio de lembretes automáticos para pacientes sobre suas consultas, garantindo que eles compareçam às mesmas. O sistema utiliza comunicação assíncrona através do Kafka para receber mensagens de agendamentos e enviar notificações por e-mail.

## Objetivo do Projeto

O principal objetivo deste projeto é implementar um serviço que escute um tópico Kafka (`consultas-agendadas`), consuma mensagens sobre consultas agendadas e envie notificações por e-mail aos pacientes. O sistema deve ser escalável, seguro e utilizar boas práticas de autenticação e autorização.

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
projeto-fiap-hospitalar-notificacoes/
│
├── src/
│ └── main/
│   ├── java/
│   │ └── com.fiap.hospitalar.notificacoes
│   │   ├── dto/request/ : Objetos de transferência de dados (DTOs) para requisições.
│   │   ├── dto/response/ : Objetos de transferência de dados (DTOs) para respostas.
│   │   ├── message/ : Configuração e implementação do consumidor Kafka.
│   │   ├── service/ : Lógica de negócios do sistema.
│   │   └── NotificationApplication.java : Classe principal que inicia a aplicação.
│   └── resources/
│       └── application.properties : Configurações da aplicação.
├── pom.xml : Arquivo de configuração do Maven.
├── Dockerfile : Arquivo para construção da imagem Docker.
├── docker-compose.yml : Arquivo para orquestração de contêineres.
└── README.md : Documentação do projeto.
```

## Segurança

A segurança do sistema é tratada com o uso do Spring Security, que fornece autenticação e autorização robustas. As principais medidas de segurança implementadas incluem:

- **Autenticação**: Os usuários devem fornecer credenciais válidas (login e senha) para acessar o sistema.
- **Proteção contra Ataques Comuns**: O sistema é configurado para proteger contra ataques comuns, como CSRF (Cross-Site Request Forgery) e XSS (Cross-Site Scripting), através de cabeçalhos de segurança e validação de entrada.

## Visão Geral do Projeto

Este projeto é um backend desenvolvido com o framework Spring Boot. O sistema é projetado para gerenciar notificações, permitindo o envio de lembretes automáticos para pacientes sobre suas consultas.

## Arquitetura

A arquitetura do projeto segue o padrão MVC (Model-View-Controller) e é organizada em pacotes que separam as responsabilidades:

- **Model**: Contém as classes que representam as entidades do sistema, como `ConsultationRequestDTO` e `ConsultationResponseDTO`.
- **Service**: Contém a lógica de negócios relacionada ao envio de notificações.
- **Message**: Configuração do consumidor Kafka que escuta o tópico de agendamentos.

## Princípios de Design e Padrões de Projeto

### Princípios de Design

1. **Single Responsibility Principle (SRP)**:
    - Cada classe tem uma única responsabilidade. Por exemplo, a classe `NotificationService` é responsável apenas pela lógica de envio de notificações.

2. **Open/Closed Principle (OCP)**:
    - As classes devem estar abertas para extensão, mas fechadas para modificação. Isso é alcançado através do uso de interfaces e classes que implementam essas interfaces.

### Padrões de Projeto

1. **MVC (Model-View-Controller)**:
    - A estrutura segue o padrão MVC, onde:
        - **Model**: Representado pelas classes de modelo (`ConsultationRequestDTO`, `ConsultationResponseDTO`).
        - **Controller**: Embora não haja um controlador tradicional, a lógica de escuta do Kafka atua como um controlador que gerencia as mensagens recebidas.
        - **View**: As respostas JSON podem ser vistas como uma forma de "view".

## Interação entre as Partes do Sistema

1. **Requisição do Serviço de Agendamento**: O serviço de agendamento publica uma mensagem no tópico Kafka `consultas-agendadas`.
2. **Kafka Listener**: O `NotificationService` escuta o tópico e consome a mensagem.
3. **Processamento da Mensagem**: O serviço processa a mensagem e envia uma notificação por e-mail ao paciente.
4. **Resposta ao Serviço de Agendamento**: O serviço de agendamento não espera uma resposta do serviço de notificações, pois a comunicação é assíncrona.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Kafka**: Sistema de mensageria para comunicação assíncrona entre serviços.
- **JavaMailSender**: Para envio de e-mails.
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
- [Kafka Documentation](https://kafka.apache.org/documentation/)
- [JavaMailSender Documentation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/mail/javamail/JavaMailSender.html)

## Conclusão

A aplicação incorpora vários princípios de design e padrões de projeto que promovem a manutenibilidade, extensibilidade e clareza do código. Esses princípios e padrões ajudam a garantir que a aplicação possa evoluir ao longo do tempo sem se tornar difícil de entender ou modificar.