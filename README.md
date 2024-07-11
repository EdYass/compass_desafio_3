# Desafio 3 - Programa de Bolsas Backend – Spring Boot

Este é o projeto de e-commerce desenvolvido como parte do Desafio 3 do Programa de Bolsas Backend – Spring Boot. 
O projeto consiste em um sistema de gerenciamento de produtos e vendas, com funcionalidades de CRUD, controle de estoque, cache, e relatórios.

## Ferramentas Utilizadas

- Java 17
- Spring Boot 3.3.1
- MySQL Server
- IntelliJ IDEA 
- Postman (para testar a API)

## Para Teste no Postman
-Adicionar Produto: 

URL: http://localhost:8080/api/products

Body (JSON):

    {
      "name": "Product 1",
      "description": "Description for product 1",
      "price": 100.00,
      "stock": 50
    }

-Adicionar Venda:

Body (JSON):

URL: http://localhost:8080/api/sales

      {
        "date": "2024-07-10T14:00:00",
        "saleProducts": [
        {
          "productId": 1,
          "quantity": 2
        }
      ]
    }

-Filtrar Vendas por Data:

URL: http://localhost:8080/api/sales/filter?startDate=2024-04-01T00:00:00&endDate=2024-04-30T23:59:59

-Obter Relatório Semanal:

URL: http://localhost:8080/api/sales/report/weekly

-Obter Relatório Mensal para um Mês Específico:

URL: http://localhost:8080/api/sales/report/monthly?year=2024&month=6
