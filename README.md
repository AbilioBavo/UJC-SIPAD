# UJC-SIPAD

Sistema Integrado para Administração de Docentes (SIPAD) - Universidade Joaquim Chissano (UJC)

## Descrição do Projeto
O SIPAD é uma plataforma digital desenvolvida para facilitar a gestão acadêmica de docentes na Universidade Joaquim Chissano (UJC). O sistema oferece um ambiente centralizado e eficiente para o cadastro e controle de cursos, disciplinas, docentes e turmas, promovendo a organização e a transparência nas atividades administrativas da universidade.

### Funcionalidades principais
- Cadastro, edição, leitura e remoção de **cursos**, **disciplinas**, **docentes** e **turmas**
- Associação de disciplinas a cursos e turmas
- Alocação de docentes a disciplinas, com histórico de alocações

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- PostgreSQL
- Lombok

## Como clonar e rodar o projeto

1. **Clone o repositório:**
   ```sh
   git clone https://github.com/AbilioBavo/UJC-SIPAD
   cd UJC-SIPAD
   ```

2. **Configure o banco de dados:**
   - Certifique-se de ter o PostgreSQL rodando.
   - Altere as credenciais em `src/main/resources/application.properties` se necessário:
     ```properties
     spring.datasource.url=jdbc:postgresql://localhost:5432/sipad_db
     spring.datasource.username=
     spring.datasource.password=
     ```

3. **Compile e rode o projeto:**
   ```sh
   clique em run na sua IDE
   ```
   O sistema estará disponível em `http://localhost:8080`.

## Como testar a API

Você pode usar o swagger em http://localhost:8080/swagger-ui/index.html ou outras feramentas para testar os endpoints.

### Criar um curso
```http
POST /api/cursos/criar
Content-Type: application/json

{
  "nome": "Engenharia Informática"
}
```

### Criar um docente
```http
POST /api/docentes/criar
Content-Type: application/json

{
  "nome": "Momade Abdul",
  "titulacaoMaxima": "Mestre",
  "endereco": "Maputo, Mocambique",
  "telefone": "840000000",
  "email": "momade@ujc.ac.mz"
}
```

### Criar uma turma
```http
POST /api/turmas/criar
Content-Type: application/json

{
  "turno": "Manhã",
  "ano": "2025",
  "semestre": "1"
}
```

### Criar uma disciplina
```http
POST /api/disciplinas/criar
Content-Type: application/json

{
  "nome": "Matemática",
  "cargaHoraria": "60"
}
```

### Listar todos os docentes
```http
GET /api/docentes/
```

### Alocar docente a disciplina
```http
PUT /api/disciplinas/1/alocar-docente
Content-Type: application/json

1
```

### Associar disciplinas a uma turma
```http
PUT /api/turmas/1/disciplinas
Content-Type: application/json

[1,2,3]
```

### Associar curso a uma turma
```http
PUT /api/turmas/1/curso
Content-Type: application/json

1
```

### Consultar histórico de docentes
```http
GET /api/historico-docentes/
```

## Estrutura de Pastas
- `controller/` - Endpoints REST
- `service/` - Lógica de negócio
- `model/` - Entidades JPA
- `repository/` - Repositórios Spring Data
- `dto/` - Objetos de transferência de dados (request/response)

## Observações
- O sistema utiliza JPA para persistência e relacionamentos entre entidades.
- O histórico de alocação de docentes é mantido automaticamente ao realocar docentes em disciplinas.
- O projeto segue boas práticas de arquitetura em camadas e uso de DTOs.

---

Desenvolvido por Bavo e Seuane para fins acadêmicos na UJC.
