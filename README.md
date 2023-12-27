# Api Products made with Spring Boot and integrated with Cloud Firestore

## How to Run the Code?

#### Add the `application.properties` file to the path: `src/main/resources/application.properties`

#### Within the `application.properties`, add the following configuration lines:
##### ```
##### spring.cloud.gcp.firestore.project-id=<project-id>
##### spring.cloud.gcp.firestore.database-id=<firestore-database-id>
##### ```

#### Ensure that you have the `application_default_credentials.json` file configured on your machine and execute the following command:
##### ```
##### cp ~/.config/gcloud/application_default_credentials.json .
##### ```

#### Make sure you have Docker installed on your machine and run the following:
##### ```
##### docker build --no-cache -t api-products .
##### ```

#### Then execute the following:
##### ```
##### docker run -p 8080:8080 api-products
##### ```


## Como rodar o código?

#### Adicione o application.properties no path: src/main/resources/application.properties

#### Dentro do application.properties adicione as seguintes linhas de configuração:
##### spring.cloud.gcp.firestore.project-id=>project-id<
##### spring.cloud.gcp.firestore.database-id=>firestore-database-id<

#### tenha certeza que vc tem o arquivo application_default_credentials.json configurado na sua máquina e execute o seguinte comando:
##### cp ~/.config/gcloud/application_default_credentials.json .

#### tenha certeza que vc tem o docker instalado na sua máquina e rode o seguinte: 
##### docker build --no-cache -t api-products .
#### depois execute o seguinte:
##### docker run -p 8080:8080 api-products