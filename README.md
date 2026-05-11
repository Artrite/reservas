# 📅 Sistema de Gestão de Salas

API REST para gerenciamento de reservas de salas, desenvolvida com Spring Boot.

## 🚀 Tecnologias
- Java 17+
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

## ⚙️ Funcionalidades
- Cadastrar, listar, atualizar e deletar reservas
- Consultar reservas por usuário
- Consultar reservas por sala

## 🛠️ Como rodar

### 1. Clone o repositório
git clone https://github.com/Artrite/reservas.git

### 2. Configure o banco de dados
Crie um arquivo `src/main/resources/application.properties` com o seguinte conteúdo:
```
spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
### 3. Rode o projeto
Abra na sua IDE e rode a classe principal, ou via terminal:
./mvnw spring-boot:run

## 📡 Endpoints
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | /reserva | Lista todas as reservas |
| GET | /reserva/{id} | Busca reserva por ID |
| GET | /reserva/{id}/usuario | Reservas de um usuário |
| GET | /reserva/{id}/sala | Reservas de uma sala |
| POST | /reserva | Cria uma reserva |
| PUT | /reserva/{id} | Atualiza uma reserva |
| DELETE | /reserva/{id} | Deleta uma reserva |
