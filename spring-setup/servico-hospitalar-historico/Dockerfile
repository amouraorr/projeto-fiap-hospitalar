FROM eclipse-temurin:17-jdk-alpine

# Defina o diretório de trabalho
WORKDIR /app

# Copie o arquivo JAR gerado para o contêiner
COPY target/historico-service-0.0.1-SNAPSHOT.jar app.jar

# Copie o diretório de recursos para o contêiner
COPY src/main/resources/ /app/src/main/resources/

# Exponha a porta que a aplicação irá rodar
EXPOSE 8086

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]