# Api Products made with Spring Boot and integrated with Cloud Firestore

## How to Run the Code?

1. Add the `application.properties` file to the path: `src/main/resources/application.properties`

2. Within the `application.properties`, add the following configuration lines:
    ```properties
    spring.cloud.gcp.firestore.project-id=<project-id>
    spring.cloud.gcp.firestore.database-id=<firestore-database-id>
    ```

3. Ensure that you have the `application_default_credentials.json` file configured on your machine and execute the following command:
    ```bash
    cp ~/.config/gcloud/application_default_credentials.json .
    ```

4. Make sure you have Docker installed on your machine and run the following:
    ```bash
    docker build --no-cache -t api-products .
    ```

5. Then execute the following:
    ```bash
    docker run -p 8080:8080 api-products
    ```

## Como rodar o código?

1. Adicione o `application.properties` no caminho: `src/main/resources/application.properties`

2. Dentro do `application.properties`, adicione as seguintes linhas de configuração:
    ```properties
    spring.cloud.gcp.firestore.project-id=<project-id>
    spring.cloud.gcp.firestore.database-id=<firestore-database-id>
    ```

3. Tenha certeza que você tem o arquivo `application_default_credentials.json` configurado na sua máquina e execute o seguinte comando:
    ```bash
    cp ~/.config/gcloud/application_default_credentials.json .
    ```

4. Tenha certeza que você tem o Docker instalado na sua máquina e rode o seguinte: 
    ```bash
    docker build --no-cache -t api-products .
    ```

5. Depois execute o seguinte:
    ```bash
    docker run -p 8080:8080 api-products
    ```
