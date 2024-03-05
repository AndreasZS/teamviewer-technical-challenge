# TeamViewer Technical Challenge

This application is created in response to TeamViewer's technical challenge 
as a part of their hiring process. 

## Installation

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

## OpenAPI and Swagger

When running locally, you can view the OpenAPI definition and Swagger UI at 
the following URIs:</br>
OpenAPI: http://localhost:8080/v3/api-docs
SwaggerUI: http://localhost:8080/swagger-ui/index.html
curl -v -X POST localhost:8080/api/products -H "Content-Type: 
application/json" -d '{"id": 1, "name": "Product 
1", "description": "product 1 description.", "price": 1.00}'

curl localhost:8080/api/orders -H 'Content-Type: application/json' -d '
{"id": 1}'
## Usage

```python
import foobar

# returns 'words'
foobar.pluralize('word')

# returns 'geese'
foobar.pluralize('goose')

# returns 'phenomenon'
foobar.singularize('phenomena')
```

## Contributing

Pull requests are welcome. For major changes, please open an issue first
to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License

[MIT](https://choosealicense.com/licenses/mit/)