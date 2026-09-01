# Sistema Administrativo de Estoque

O Documento oficial do Projeto pode ser encontrado no link a seguir:
https://docs.google.com/document/d/16ZrPXURQfLCX1YMhJkyjJQRCDnOd6EhwkVabb6KKCJs/edit?usp=sharing

## Definição de Escopo

### 1. Mapeamento de Usuários

* **Administrador:** responsável pelo cadastro e gerenciamento de produtos, fornecedores, categorias e usuários do sistema.
* **Funcionário (Estoquista):** responsável pelo registro de entrada e saída de produtos, atualização do estoque e consulta da disponibilidade dos produtos.

### 2. Requisitos Técnicos

* **Frontend:** interface web responsiva para gerenciamento do estoque.
* **Backend & API REST:** servidor responsável pelo processamento das regras de negócio e disponibilização dos endpoints da aplicação.
* **Persistência de Dados:** banco de dados relacional para armazenamento de produtos, fornecedores, movimentações e usuários.
* **Validação:** o sistema deve impedir a saída de produtos quando a quantidade disponível em estoque for insuficiente.
* **Configuração por Ambiente:** utilização de variáveis de ambiente para separar as configurações de desenvolvimento e produção.

## Prototipagem e Contratos

### 1. Protótipos de Integração (Wireframes)

* **Tela de Produtos:** permite visualizar, cadastrar e atualizar os produtos do estoque.
* **Tela de Movimentação de Estoque:** permite registrar entradas e saídas de produtos.
* **Dashboard Administrativo:** apresenta informações gerais sobre o estoque e suas movimentações.

### 2. Contratos da API

#### Endpoint: Buscar Produtos

* **Rota:** `GET /api/produtos`
* **Parâmetros:** `nome` e `categoria`.

**Resposta de Sucesso (200 OK):**

```json
[
  {
    "id": 1,
    "nome": "Teclado Mecânico",
    "categoria": "Periféricos",
    "quantidade": 25
  }
]
```

#### Endpoint: Registrar Movimentação

* **Rota:** `POST /api/movimentacoes`

**Corpo da requisição:**

```json
{
  "produtoId": 1,
  "tipo": "SAIDA",
  "quantidade": 5
}
```

**Resposta de Erro (400 Bad Request):**

`Estoque insuficiente para realizar a saída do produto.`

### 3. Documentação de Fluxo e Comunicação

* O **Frontend** consumirá os serviços da API utilizando requisições HTTP.
* O **Backend** validará os dados recebidos antes da execução das regras de negócio.
* As respostas da API seguirão o padrão **JSON**.
* As mensagens de erro serão padronizadas para facilitar o tratamento e a apresentação das informações no frontend.

  ✅ Estrutura Completa Criada

      backend/
      ├── pom.xml                                    # Maven config (Spring Boot 3.3.2, Java 21)
      ├── src/main/
      │   ├── java/com/estoque/
      │   │   ├── SistemaEstoqueApplication.java     # Classe principal
      │   │   ├── controller/
      │   │   │   ├── ProdutoController.java         # GET/POST/PUT/DELETE /api/produtos
      │   │   │   └── MovimentacaoController.java    # POST/GET /api/movimentacoes
      │   │   ├── dto/
      │   │   │   ├── ProdutoRequestDTO.java         # Validação @NotBlank, @Size, @Min
      │   │   │   ├── ProdutoResponseDTO.java
      │   │   │   ├── MovimentacaoRequestDTO.java    # Validação @NotNull, @Min(1)
      │   │   │   └── MovimentacaoResponseDTO.java
      │   │   ├── entity/
      │   │   │   ├── Produto.java                   # JPA Entity
      │   │   │   ├── Movimentacao.java              # JPA Entity
      │   │   │   └── TipoMovimentacao.java          # Enum ENTRADA/SAIDA
      │   │   ├── repository/
      │   │   │   ├── ProdutoRepository.java         # JpaRepository
      │   │   │   └── MovimentacaoRepository.java    # JpaRepository
      │   │   ├── service/
      │   │   │   ├── ProdutoService.java            # Interface + impl dummy
      │   │   │   ├── MovimentacaoService.java       # Interface
      │   │   │   └── MovimentacaoServiceImpl.java   # Implementação com TODO + dummy
      │   │   └── exception/
      │   │       ├── BusinessException.java         # Exceção de negócio
      │   │       └── GlobalExceptionHandler.java    # @RestControllerAdvice (400, 422, 404, 500)
      │   └── resources/
      │       └── application.yml                    # H2 in-memory, porta 8080, context-path /api

    ────────────────────────────────────

    🔑 Endpoints Prontos (com validação DTO ativada via @Valid)

      Método  Rota                     Descrição
      ──────  ───────────────────────  ────────────────────────────────────────────────────────
      POST    /api/produtos            Criar produto (valida nome, categoria, qtd ≥ 0)
      GET     /api/produtos            Listar paginado (filtros: ?nome=&categoria=)
      GET     /api/produtos/{id}       Buscar por ID
      PUT     /api/produtos/{id}       Atualizar
      DELETE  /api/produtos/{id}       Deletar
      POST    /api/movimentacoes       Registrar movimentação (valida produtoId, tipo, qtd ≥ 1)
      GET     /api/movimentacoes       Listar todas
      GET     /api/movimentacoes/{id}  Buscar por ID

    ────────────────────────────────────

    🚀 Como Rodar

      ─ bash
      cd ~/Projetos/github/estudos/sistemaestoque/backend
      source /etc/profile  # ou reinicie o terminal
      mvn spring-boot:run

    A API sobe em: http://localhost:8080/api/...
    H2 Console: http://localhost:8080/h2-console (JDBC URL: jdbc:h2:mem:estoque)

