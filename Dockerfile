#Etapa de build
# Usar a imagem oficial do Maven para construir a aplicação
FROM maven:3.8.5-openjdk-17 AS build

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o pom.xml e as dependências
COPY pom.xml .
COPY src ./src

# Fazer o build da aplicação
RUN mvn clean package -DskipTests

#RunTime
# Usar uma imagem menor para executar a aplicação
FROM openjdk:17-jdk-slim

# Definir o diretório de trabalho
WORKDIR /app

# Copiar o arquivo JAR gerado
COPY --from=build /app/target/*.jar app.jar

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]