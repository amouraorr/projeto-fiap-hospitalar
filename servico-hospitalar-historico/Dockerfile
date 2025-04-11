FROM eclipse-temurin:17-jdk-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado para o contêiner
COPY target/servico-hospitalar-historico-0.0.1-SNAPSHOT.jar app.jar

# Exponha a porta que a aplicação irá rodar
EXPOSE 8081

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]