# Catalogo API

API REST de catalogo de produtos desenvolvida com **Spring Boot 4.0.3** e **Java 21**.
Projeto didatico para aula de desenvolvimento de APIs REST.

## Como rodar a aplicacao

```bash
./mvnw spring-boot:run
```

A aplicacao vai iniciar na porta **8080**.

## Swagger UI (Documentacao interativa)

Apos iniciar a aplicacao, acesse:

```
http://localhost:8080/swagger-ui/index.html
```

## Endpoints disponives

| Metodo | Endpoint                  | Descricao              | Status de sucesso |
|--------|---------------------------|------------------------|-------------------|
| GET    | /api/v1/produtos          | Listar todos           | 200               |
| GET    | /api/v1/produtos/{id}     | Buscar por ID          | 200               |
| POST   | /api/v1/produtos          | Criar produto          | 201               |
| PUT    | /api/v1/produtos/{id}     | Atualizar produto      | 200               |
| DELETE | /api/v1/produtos/{id}     | Deletar produto        | 204               |

## Exemplos com curl

### Listar todos os produtos

```bash
curl -s http://localhost:8080/api/v1/produtos | json_pp
```

### Buscar produto por ID

```bash
curl -s http://localhost:8080/api/v1/produtos/1 | json_pp
```

### Criar um novo produto

```bash
curl -s -X POST http://localhost:8080/api/v1/produtos \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Relogio Digital",
    "descricao": "Relogio digital a prova d agua",
    "preco": 89.90
  }' | json_pp
```

### Atualizar um produto

```bash
curl -s -X PUT http://localhost:8080/api/v1/produtos/1 \
  -H "Content-Type: application/json" \
  -d '{
    "nome": "Camiseta Basica Premium",
    "descricao": "Camiseta 100% algodao premium",
    "preco": 69.90
  }' | json_pp
```

### Deletar um produto

```bash
curl -s -X DELETE http://localhost:8080/api/v1/produtos/3 -w "\nHTTP Status: %{http_code}\n"
```

## Colecao Postman

Importe o JSON abaixo no Postman (File > Import > Raw text):

```json
{
  "info": {
    "name": "Catalogo API",
    "schema": "https://schema.getpostman.com/json/collection/v2.1.0/collection.json"
  },
  "variable": [
    {
      "key": "baseUrl",
      "value": "http://localhost:8080/api/v1/produtos"
    }
  ],
  "item": [
    {
      "name": "Listar todos os produtos",
      "request": {
        "method": "GET",
        "url": "{{baseUrl}}"
      }
    },
    {
      "name": "Buscar produto por ID",
      "request": {
        "method": "GET",
        "url": "{{baseUrl}}/1"
      }
    },
    {
      "name": "Criar novo produto",
      "request": {
        "method": "POST",
        "url": "{{baseUrl}}",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"nome\": \"Relogio Digital\",\n  \"descricao\": \"Relogio digital a prova d agua\",\n  \"preco\": 89.90\n}"
        }
      }
    },
    {
      "name": "Atualizar produto",
      "request": {
        "method": "PUT",
        "url": "{{baseUrl}}/1",
        "header": [
          {
            "key": "Content-Type",
            "value": "application/json"
          }
        ],
        "body": {
          "mode": "raw",
          "raw": "{\n  \"nome\": \"Camiseta Basica Premium\",\n  \"descricao\": \"Camiseta 100% algodao premium\",\n  \"preco\": 69.90\n}"
        }
      }
    },
    {
      "name": "Deletar produto",
      "request": {
        "method": "DELETE",
        "url": "{{baseUrl}}/3"
      }
    }
  ]
}
```

## Estrutura do projeto

```
src/main/java/com/saas/catalogoapi/
├── controller/
│   └── ProdutoController.java    # Endpoints REST
├── service/
│   └── ProdutoService.java       # Logica de negocio
├── dto/
│   ├── ProdutoRequest.java       # Dados de entrada
│   └── ProdutoResponse.java      # Dados de saida
├── exception/
│   ├── ProdutoNotFoundException.java   # Excecao customizada
│   └── GlobalExceptionHandler.java     # Tratamento global de erros
└── CatalogoApiApplication.java   # Classe principal
```
