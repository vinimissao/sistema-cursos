# Sistema de Cadastro de Cursos - ADO 4

Sistema completo de gerenciamento de cursos desenvolvido com Spring Boot, incluindo área administrativa protegida e área pública para consulta de cursos.

## 📋 Descrição

Este projeto é um sistema web completo para cadastro e gerenciamento de cursos, desenvolvido como parte da ADO 4. O sistema possui duas áreas distintas:

- **Área Administrativa (Protegida)**: Permite que administradores cadastrem, editem, listem e removam cursos
- **Área Pública**: Permite que visitantes consultem os cursos cadastrados

## 🛠️ Tecnologias Utilizadas

- **Spring Boot 3.2.0**
- **Spring Security** - Autenticação e autorização
- **Spring Data JPA** - Persistência de dados
- **Thymeleaf** - Template engine para páginas web
- **Bean Validation** - Validação de dados
- **H2 Database** - Banco de dados em memória
- **Bootstrap 5.3.2** - Framework CSS
- **jQuery 3.7.1** - Biblioteca JavaScript

## 📦 Estrutura do Projeto

```
sistema-cursos/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/escola/
│   │   │       ├── SistemaCursosApplication.java
│   │   │       ├── config/
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   └── DataInitializer.java
│   │   │       ├── controller/
│   │   │       │   ├── HomeController.java
│   │   │       │   ├── WebCursoController.java
│   │   │       │   ├── PublicoCursoController.java
│   │   │       │   ├── ApiCursoController.java
│   │   │       │   └── ApiPublicoCursoController.java
│   │   │       ├── model/
│   │   │       │   ├── Curso.java
│   │   │       │   └── Usuario.java
│   │   │       ├── repository/
│   │   │       │   ├── CursoRepository.java
│   │   │       │   └── UsuarioRepository.java
│   │   │       └── service/
│   │   │           ├── CursoService.java
│   │   │           ├── UsuarioService.java
│   │   │           └── CustomUserDetailsService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── templates/
│   │           ├── login.html
│   │           ├── admin/
│   │           │   ├── lista-cursos.html
│   │           │   └── form-curso.html
│   │           └── publico/
│   │               ├── index.html
│   │               ├── lista-cursos.html
│   │               └── detalhes-curso.html
│   └── test/
├── pom.xml
└── README.md
```

## 🚀 Como Executar o Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior
- IDE de sua preferência (IntelliJ IDEA, Eclipse, VS Code, etc.)

### Passos para Execução

1. **Clone o repositório ou extraia o projeto**

2. **Navegue até o diretório do projeto**
   ```bash
   cd sistema-cursos
   ```

3. **Compile o projeto usando Maven**
   ```bash
   mvn clean install
   ```

4. **Execute a aplicação**
   ```bash
   mvn spring-boot:run
   ```
   
   Ou execute diretamente a classe `SistemaCursosApplication.java` pela sua IDE.

5. **Acesse a aplicação no navegador**
   - URL: `http://localhost:8080`
   - A aplicação estará rodando na porta 8080

## 🔐 Credenciais de Acesso

Ao iniciar a aplicação pela primeira vez, um usuário administrador é criado automaticamente:

- **Usuário**: `admin`
- **Senha**: `admin123`

## 📱 Funcionalidades

### Área Pública (Não Logada)

- **Home** (`/`): Página inicial do sistema
- **Lista de Cursos** (`/cursos/publico`): Visualização de todos os cursos cadastrados
  - Busca por nome
  - Filtro por categoria
- **Detalhes do Curso** (`/cursos/publico/{id}`): Visualização detalhada de um curso específico

### Área Administrativa (Logada)

- **Lista de Cursos** (`/admin/cursos`): Gerenciamento de todos os cursos
- **Novo Curso** (`/admin/cursos/novo`): Formulário para cadastrar novo curso
- **Editar Curso** (`/admin/cursos/editar/{id}`): Formulário para editar curso existente
- **Excluir Curso**: Remoção de curso (via botão na lista)

### API REST

#### API Administrativa (Protegida - Requer autenticação)

- `GET /api/admin/cursos` - Lista todos os cursos
- `GET /api/admin/cursos/{id}` - Busca curso por ID
- `POST /api/admin/cursos` - Cria novo curso
- `PUT /api/admin/cursos/{id}` - Atualiza curso existente
- `DELETE /api/admin/cursos/{id}` - Remove curso

#### API Pública (Acesso livre)

- `GET /api/cursos` - Lista todos os cursos
- `GET /api/cursos/{id}` - Busca curso por ID
- `GET /api/cursos/categoria/{categoria}` - Busca cursos por categoria
- `GET /api/cursos/busca?nome={nome}` - Busca cursos por nome

## 🗄️ Banco de Dados

O projeto utiliza o **H2 Database** (banco em memória). Para acessar o console do H2:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:cursodb`
- Usuário: `sa`
- Senha: (deixe em branco)

**Nota**: Os dados são perdidos quando a aplicação é reiniciada, pois o banco é em memória.

## 📝 Dados Iniciais

Ao iniciar a aplicação pela primeira vez, os seguintes dados são criados automaticamente:

- 1 usuário administrador (admin/admin123)
- 4 cursos de exemplo:
  - Java para Iniciantes
  - Spring Boot Avançado
  - Design de Interfaces
  - Banco de Dados SQL

## 🔒 Segurança

- **Spring Security** configurado com autenticação baseada em formulário
- Área administrativa protegida (requer role ADMIN)
- API administrativa protegida (requer autenticação)
- API pública acessível sem autenticação
- Senhas criptografadas com BCrypt

## 🧪 Testando a API

### Exemplo usando cURL:

**Listar cursos (público):**
```bash
curl http://localhost:8080/api/cursos
```

**Criar curso (requer autenticação):**
```bash
curl -X POST http://localhost:8080/api/admin/cursos \
  -H "Content-Type: application/json" \
  -u admin:admin123 \
  -d '{
    "nome": "Novo Curso",
    "descricao": "Descrição do novo curso",
    "cargaHoraria": 20,
    "categoria": "Programação",
    "preco": 199.90
  }'
```

## 📄 Validações

O sistema implementa validações Bean Validation nas entidades:

- **Curso**:
  - Nome: obrigatório, 3-100 caracteres
  - Descrição: obrigatória, 10-500 caracteres
  - Carga horária: obrigatória, valor positivo
  - Categoria: obrigatória, 2-50 caracteres
  - Preço: obrigatório, valor positivo

- **Usuario**:
  - Username: obrigatório, 3-50 caracteres, único
  - Senha: obrigatória, mínimo 6 caracteres
  - Nome: obrigatório, 3-100 caracteres

## 🐛 Solução de Problemas

### Erro de porta em uso
Se a porta 8080 estiver em uso, altere no arquivo `application.properties`:
```properties
server.port=8081
```

### Erro de compilação
Certifique-se de que está usando Java 17 ou superior:
```bash
java -version
```

## 📚 Documentação Adicional

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [Thymeleaf Documentation](https://www.thymeleaf.org/documentation.html)

## 👨‍💻 Desenvolvido por

Projeto desenvolvido como parte da ADO 4 - Sistema de Cadastro de Cursos

## 📄 Licença

Este projeto é parte de um trabalho acadêmico.

