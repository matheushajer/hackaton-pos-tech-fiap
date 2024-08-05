# 🚀 HACKATHON - FIAP PosTech

Este projeto foi elaborado cumprindo os objetivos com o adicional de criação de usuários.

## 🎯 Objetivos

Desenvolver um sistema eficiente para o processamento de pagamentos de operadoras de cartão de crédito com as seguintes funcionalidades:

- 📥 Receber os dados das transações com cartão de crédito;
- ✅ Validar se o cartão do cliente possui limite disponível para a realização da compra;
- 🔍 Garantir a verificação precisa e em tempo real do limite de crédito dos clientes.

## 📑 Documentação da API

########

## 🚀 Deploy

### ⚙️ Deploy local (Docker Compose):

Basta acessar [Dockerfiles](https://github.com/matheushajer/hackaton-pos-tech-fiap/tree/master/deployment/images) para encontrar os Dockerfiles das imagens criadas.

Cada Dockerfile é utilizado para um microserviço diferente e para o banco de dados Postgresql.

Para facilitar a execução basta ir até [docker-compose.yml](https://github.com/matheushajer/hackaton-pos-tech-fiap/blob/master/deployment/docker-compose.yml), baixar para sua máquina e utilizar o comando abaixo:

```bash
docker-compose up
```

🔗 Mais informações em: [Docker Compose Documentation](https://docs.docker.com/compose/)

### 🌐 Links:
- 📌 Eureka: [http://localhost:8081](http://localhost:8081)
- 📌 Gateway: [http://localhost:8082](http://localhost:8082)
- 📄 Documentação Collection: [Postman Collection Hackathon FIAP](https://github.com/matheushajer/hackaton-pos-tech-fiap/blob/master/Hackathon-APIs.postman_collection.json)
- 🖼️ Repositório de imagens: [Repositorio Docker Hub](https://hub.docker.com/repository/docker/yuriesena/hackaton-fiap/tags)

## 👥 Autores

- 👤 Cleyton Sales
- 👤 Déborah Souza
- 👤 Karoline Leite
- 👤 Matheus Hajer
- 👤 Yuri Sena
