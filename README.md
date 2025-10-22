# 🛠️ API de Gerenciamento de Projetos e Tarefas

API RESTful desenvolvida como parte de uma avaliação acadêmica, utilizando **Spring Boot**, **Spring Data JPA** e **MySQL** para gerenciar projetos e suas tarefas associadas.

---

## 🚀 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.5.6  
- Spring Data JPA (Hibernate)  
- Spring Web (API RESTful)  
- Spring Validation (Validação de entrada)  
- MySQL (Banco de Dados Relacional)  
- Lombok (Redução de boilerplate)  
- Maven (Gerenciador de Dependências)  

---

## 📋 Requisitos Funcionais (Endpoints)

### 🔹 Endpoints de Projetos (`/api/projetos`)

| Verbo HTTP | Endpoint              | Descrição                                  |
|------------|-----------------------|--------------------------------------------|
| POST       | `/api/projetos`       | Cria um novo projeto.                      |
| GET        | `/api/projetos`       | Lista todos os projetos cadastrados.       |
| GET        | `/api/projetos/{id}`  | Busca um projeto específico pelo seu ID.   |
| PUT        | `/api/projetos/{id}`  | Atualiza os dados de um projeto existente. |
| DELETE     | `/api/projetos/{id}`  | Remove um projeto e suas tarefas.          |

### 🔹 Endpoints de Tarefas (`/api/tarefas`)

| Verbo HTTP | Endpoint              | Descrição                                       |
|------------|-----------------------|-------------------------------------------------|
| POST       | `/api/tarefas`        | Cria uma nova tarefa (associada a um projeto).  |
| GET        | `/api/tarefas`        | Lista todas as tarefas de todos os projetos.    |
| GET        | `/api/tarefas/{id}`   | Busca uma tarefa específica pelo seu ID.        |
| PUT        | `/api/tarefas/{id}`   | Atualiza os dados de uma tarefa existente.      |
| DELETE     | `/api/tarefas/{id}`   | Remove uma tarefa.                              |

### 🔹 Endpoint Adicional

| Verbo HTTP | Endpoint                        | Descrição                                      |
|------------|----------------------------------|------------------------------------------------|
| GET        | `/api/projetos/{id}/tarefas`    | Lista todas as tarefas de um projeto específico.|

---

## 🔧 Como Configurar e Executar Localmente

### ✅ Pré-requisitos

- JDK 21 (ou superior)  
- Maven 3.x  
- MySQL (rodando na porta 3306)

### 📁 1. Clonar o Repositório

```bash
git clone https://github.com/Sasagayo29/api-gerenciamento-projetos.git
cd demo
```
# 🛠️ API de Gerenciamento de Projetos e Tarefas

API RESTful desenvolvida como parte de uma avaliação acadêmica, utilizando **Spring Boot**, **Spring Data JPA** e **MySQL** para gerenciar projetos e suas tarefas associadas.

---

## 🚀 Tecnologias Utilizadas

- Java 21  
- Spring Boot 3.5.6  
- Spring Data JPA (Hibernate)  
- Spring Web (API RESTful)  
- Spring Validation (Validação de entrada)  
- MySQL (Banco de Dados Relacional)  
- Lombok (Redução de boilerplate)  
- Maven (Gerenciador de Dependências)  

---

## 📋 Requisitos Funcionais (Endpoints)

### 🔹 Endpoints de Projetos (`/api/projetos`)

| Verbo HTTP | Endpoint              | Descrição                                  |
|------------|-----------------------|--------------------------------------------|
| POST       | `/api/projetos`       | Cria um novo projeto.                      |
| GET        | `/api/projetos`       | Lista todos os projetos cadastrados.       |
| GET        | `/api/projetos/{id}`  | Busca um projeto específico pelo seu ID.   |
| PUT        | `/api/projetos/{id}`  | Atualiza os dados de um projeto existente. |
| DELETE     | `/api/projetos/{id}`  | Remove um projeto e suas tarefas.          |

### 🔹 Endpoints de Tarefas (`/api/tarefas`)

| Verbo HTTP | Endpoint              | Descrição                                       |
|------------|-----------------------|-------------------------------------------------|
| POST       | `/api/tarefas`        | Cria uma nova tarefa (associada a um projeto).  |
| GET        | `/api/tarefas`        | Lista todas as tarefas de todos os projetos.    |
| GET        | `/api/tarefas/{id}`   | Busca uma tarefa específica pelo seu ID.        |
| PUT        | `/api/tarefas/{id}`   | Atualiza os dados de uma tarefa existente.      |
| DELETE     | `/api/tarefas/{id}`   | Remove uma tarefa.                              |

### 🔹 Endpoint Adicional

| Verbo HTTP | Endpoint                        | Descrição                                      |
|------------|----------------------------------|------------------------------------------------|
| GET        | `/api/projetos/{id}/tarefas`    | Lista todas as tarefas de um projeto específico.|

---

## 🔧 Como Configurar e Executar Localmente

### ✅ Pré-requisitos

- JDK 21 (ou superior)  
- Maven 3.x  
- MySQL (rodando na porta 3306)

### 📁 1. Clonar o Repositório

```bash
git clone [URL_DO_SEU_REPOSITORIO_AQUI]
cd demo
```

### 🛠️ 2. Configurar o Banco de Dados

Crie o schema no MySQL:

```bash
CREATE DATABASE db_projetos;
```

Atualize o arquivo `src/main/resources/application.properties` com suas credenciais:

```bash
spring.datasource.url=jdbc:mysql://localhost:3306/db_projetos
spring.datasource.username=SEU_USUARIO_MYSQL
spring.datasource.password=SUA_SENHA_MYSQL
```

### ▶️ 3. Executar a Aplicação

Você pode executar diretamente via sua IDE ou terminal:

```bash
# Limpa o projeto e executa a aplicação
mvn clean spring-boot:run
```

A aplicação estará disponível em: http://localhost:8080

## 📦 Estrutura do Projeto
```bash
├── classes
│   ├── br
│   │   └── com
│   │       └── riquelmytrabalho
│   │           └── demo
│   │               ├── controller
│   │               │   ├── ProjetoController.class
│   │               │   └── TarefaController.class
│   │               ├── exception
│   │               │   ├── GlobalExceptionHandler.class
│   │               │   └── ResourceNotFoundException.class
│   │               ├── model
│   │               │   ├── Projeto.class
│   │               │   ├── StatusTarefa.class
│   │               │   └── Tarefa.class
│   │               ├── repository
│   │               │   ├── ProjetoRepository.class
│   │               │   └── TarefaRepository.class
│   │               ├── service
│   │               │   ├── ProjetoService.class
│   │               │   └── TarefaService.class
│   │               └── DemoApplication.class
│   └── application.properties
├── generated-sources
│   └── annotations
├── generated-test-sources
│   └── test-annotations
└── test-classes
    └── br
        └── com
            └── riquelmytrabalho
                └── demo
                    └── DemoApplicationTests.class
```

## 📝 Licença

Este projeto foi desenvolvido para fins acadêmicos.
Sinta-se livre para utilizar como referência em estudos ou projetos pessoais.

## 🙋‍♂️ Autor

Riquelmy Miyasawa Borges

<a href="mailto:riquelmymiyasawaborges@gmail.com"><img src="https://img.shields.io/badge/-Gmail-%23333?style=for-the-badge&logo=gmail&logoColor=white" target="_blank"></a>
  <a href="https://www.linkedin.com/in/riquelmy-miyasawa-borges" target="_blank"><img src="https://img.shields.io/badge/-LinkedIn-%230077B5?style=for-the-badge&logo=linkedin&logoColor=white"></a>
