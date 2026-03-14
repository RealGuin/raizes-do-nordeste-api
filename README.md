# README – Raízes do Nordeste API

API REST desenvolvida em Spring Boot para gerenciamento de pedidos do restaurante Raízes do Nordeste.

A API permite realizar operações básicas de um sistema de pedidos, incluindo:


- Cadastro de usuários
- Autenticação com token JWT
- Cadastro de produtos
- Cadastro de unidades
- Criação de pedidos
- Adição de itens ao pedido
- Simulação de pagamento
- Consulta de pedidos
- Criação de estoque


Este projeto foi desenvolvido para fins acadêmicos como atividade prática de desenvolvimento de uma API REST.

O projeto foi gerado utilizando o Spring Boot Initializr:
- https://start.spring.io/

---------------------------------------------------------------------

# TESTE RÁPIDO DA API

Após iniciar a aplicação, você pode testar rapidamente a API utilizando o Swagger ou o Postman.

1) Abra o Swagger no navegador:
- http://localhost:8080/swagger-ui/index.html

2) Crie um usuário utilizando o endpoint:
- POST /usuarios

3) Realize login utilizando:
- POST /auth/login

4) Utilize os demais endpoints para criar produtos, unidades e pedidos.

Também é possível utilizar a collection do Postman incluída no projeto para testar o fluxo completo da aplicação:
- Raizes do Nordeste API - Testes.postman_collection
- Raizes API Local.postman_environment

---------------------------------------------------------------------

# LINKS PARA ACESSO RÁPIDO

Github
- https://github.com/RealGuin/raizes-do-nordeste-api.git

JSON WEB TOKENS 
- https://www.jwt.io/

Swagger
- http://localhost:8080/swagger-ui/index.html
- http://localhost:8080/swagger-ui/index.html#/
- http://localhost:8080/swagger-ui.html

---------------------------------------------------------------------

# REQUISITOS

Para executar o projeto é necessário ter instalado:
- Java 21
- O projeto inclui o Maven Wrapper (mvnw)
- Git (opcional, para clonar o repositório)
- Postman (opcional, para testar os endpoints)
- IDE (opcional, como IntelliJ IDEA, Eclipse ou VS Code)


Banco de dados utilizado:
- H2 Database (modo arquivo)

Framework utilizado:
- Spring Boot 4.0.3

Principais dependências:
- Spring Web
- Spring Data JPA
- Spring Security
- Validation (Bean Validation)
- Lombok (não utilizado)
- H2 Database
- JWT (JJWT)
- SpringDoc OpenAPI (Swagger)

---------------------------------------------------------------------

# CLONAR O PROJETO

Clone o repositório do GitHub:
- git clone https://github.com/RealGuin/raizes-do-nordeste-api.git

Depois entre na pasta do projeto:
- cd raizes-do-nordeste-api

---------------------------------------------------------------------

# VARIÁVEIS DE AMBIENTE

Este projeto não utiliza arquivo .env.

As configurações da aplicação estão definidas no arquivo:
- src/main/resources/application.properties

Caso necessário, essas configurações podem ser adaptadas para variáveis de ambiente em outros ambientes de execução.

---------------------------------------------------------------------

# INSTALAR DEPENDÊNCIAS

Todas as dependências do projeto são gerenciadas pelo Maven através do arquivo:
- pom.xml

O projeto possui o Maven Wrapper.

Para instalar todas as dependências execute no terminal dentro da pasta do projeto:
- ./mvnw clean install

O Maven fará automaticamente o download de todas as bibliotecas necessárias.

---------------------------------------------------------------------

# BANCO DE DADOS

O projeto utiliza H2 Database configurado em modo arquivo.

Acesso ao console do banco:
- http://localhost:8080/h2-console

Configuração de conexão:

JDBC URL:
- jdbc:h2:file:./data/raizesdb

User Name:
- sa

Password:
- (em branco)

Características do banco:
- Não é necessário instalar um banco de dados externo
- O banco é criado automaticamente pela aplicação
- Os dados ficam armazenados em um arquivo local
- Os dados permanecem salvos mesmo após reiniciar a aplicação

A estrutura do banco de dados é criada automaticamente pelo Hibernate (JPA) com base nas entidades do projeto.

---------------------------------------------------------------------

# LOGS DA APLICAÇÃO

A aplicação gera logs em arquivos .txt dentro da pasta:
- logs/

Esses logs podem ser utilizados para acompanhar a execução da API e registrar eventos importantes, como:
- criação de pedidos
- pagamentos
- login de usuários

---------------------------------------------------------------------

# INICIAR A API

Para iniciar a aplicação execute no terminal dentro da pasta do projeto:
- ./mvnw spring-boot:run

Para interromper a execução da aplicação:
- Ctrl + C

Também é possível executar a aplicação diretamente pela IDE através da classe:
- RaizesnovoapiApplication.java

Após iniciar a aplicação, a API estará disponível em:
- http://localhost:8080

---------------------------------------------------------------------

# ACESSAR A DOCUMENTAÇÃO DA API

A documentação da API pode ser acessada através do Swagger (OpenAPI).

Links disponíveis:
- http://localhost:8080/swagger-ui.html
- http://localhost:8080/swagger-ui/index.html

No Swagger é possível:
- visualizar todos os endpoints da API
- consultar modelos de requisição e resposta
- testar os endpoints diretamente pelo navegador

---------------------------------------------------------------------

# TESTAR A API COM POSTMAN

Foi criada uma collection no Postman contendo testes dos principais endpoints da API (fluxo principal).

Configuração do Environment no Postman:

Variável:
- baseUrl

Valor:
- http://localhost:8080

Exemplo de uso de endpoint:
- {{baseUrl}}/usuarios

Fluxo recomendado para testar a API:

1) Criar usuário
2) Realizar login
3) Criar produto
4) Criar unidade
5) Criar pedido
6) Adicionar item ao pedido
7) Realizar pagamento mock
8) Consultar pedidos

---------------------------------------------------------------------

# FLUXO PRINCIPAL DA APLICAÇÃO

Fluxo típico de uso da API:

1) Cadastro de usuário
2) Login e geração de token JWT
3) Cadastro de produtos
4) Cadastro de unidade
5) Criação de pedido
6) Adição de itens ao pedido
7) Simulação de pagamento

O pagamento é realizado por um gateway mock que pode retornar três resultados:
- APROVADO (70% de probabilidade)
- RECUSADO (20% de probabilidade)
- ERRO (10% de probabilidade)

Esses resultados são gerados de forma probabilística apenas para simulação.

Caso necessário, as proporções podem ser alteradas diretamente na classe:
- MockPagamentoGateway

---------------------------------------------------------------------

# TECNOLOGIAS UTILIZADAS
- Java 21
- Spring Boot
- Spring Boot Starter Web
- Spring Boot Starter Security
- Spring Boot Starter Data JPA
- H2 Database
- JWT (JJWT)
- Swagger / OpenAPI (Springdoc)
- Maven
- Lombok
- Hibernate (JPA)

---------------------------------------------------------------------

# OBSERVAÇÕES
- O banco de dados utilizado é H2 em modo arquivo.
- A aplicação gera logs em arquivo .txt para registro das operações.
- O pagamento é apenas uma simulação (mock), não havendo integração com serviços externos.
- A documentação dos testes foi feita através de uma collection do Postman incluída no projeto.
- O projeto inclui o Maven Wrapper (mvnw), portanto não é necessário instalar o Maven manualmente.
- Atualmente o sistema permite criar pedidos mesmo que não exista estoque do produto.
- A aplicação não utiliza controle de permissões por roles.
- O valor total do pedido é calculado automaticamente com base nos itens adicionados ao pedido.

