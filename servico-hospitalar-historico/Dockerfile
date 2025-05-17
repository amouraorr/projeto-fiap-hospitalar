# Etapa 1: Build do projeto
FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /build
COPY . .
# Executa o build apenas do módulo desejado, resolvendo dependências e o POM pai
RUN mvn -pl servico-hospitalar-historico -am clean package -DskipTests

# Etapa 2: Imagem final
FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/servico-hospitalar-historico/target/servico-hospitalar-historico-1.0.0.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]