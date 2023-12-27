# Usa a imagem base do OpenJDK 11
FROM gcr.io/distroless/java17-debian12

# Define o diretório de trabalho dentro do contêiner
WORKDIR /app

# Copia o JAR da aplicação para o contêiner
COPY target/ApiProducts-0.0.1-SNAPSHOT.jar app.jar

# Copia as credenciais para o contêiner
COPY application_default_credentials.json .

# Define a variável de ambiente
ENV GOOGLE_APPLICATION_CREDENTIALS="/app/application_default_credentials.json"

# Expõe a porta em que a aplicação está em execução
EXPOSE 8080

# Comando para executar a aplicação quando o contêiner for iniciado
CMD ["app.jar"]
