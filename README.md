# Api Products made with Spring Boot and integrated with Cloud Firestore

## Como rodar o código?

#### Adicione o application.properties no path: src/main/resources/application.properties

#### Dentro do application.properties adicione as seguintes linhas de configuração:
##### spring.cloud.gcp.firestore.project-id=<<project-id>>
##### spring.cloud.gcp.firestore.database-id=<<firestore-database-id>>

#### tenha certeza que vc tem o arquivo application_default_credentials.json configurado na sua máquina e execute o seguinte comando:
##### cp ~/.config/gcloud/application_default_credentials.json .

#### tenha certeza que vc tem o docker instalado na sua máquina e rode o seguinte: 
##### docker build --no-cache -t api-products .
#### depois execute o seguinte:
##### docker run -p 8080:8080 api-products