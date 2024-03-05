# TeamViewer Technical Challenge

This application is created in response to TeamViewer's technical challenge 
as a part of their hiring process. 

## Running the Application

Execute the following commands from the project directory unless otherwise 
specified.

### Build the application

Use the maven wrapper included with the project.
```bash
./mvnw clean package
```

### Build the docker image

If you do not have Docker installed on your machine, you must install it. 
You can use the package manager of your choice or download it from here: 
https://www.docker.com/get-started/.
```bash
docker build -t teamviewer/technical-challenge .
```

### Run using docker compose

Run the application using Docker Compose. This will build and manage 
multiple services in Docker containers. 
```bash
docker compose up
```

### Stop the application

Use docker compose to stop the application. 
```bash
docker compose down
```
<strong>NOTE:</strong> this will clear any data from the database since the 
postgres container will be shutdown.

## OpenAPI and Swagger

When running locally, you can view the OpenAPI definition and Swagger UI at 
the following URIs:</br>
OpenAPI: http://localhost:8080/v3/api-docs
</br>
SwaggerUI: http://localhost:8080/swagger-ui/index.html

## Tests

By default, tests will be run when building the application. If you would 
like to execute tests separately you can do so using the maven wrapper.
```bash
./mvnw clean test
```

## License

[MIT](https://choosealicense.com/licenses/mit/)