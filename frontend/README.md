# StockOS — Frontend Vue 3

Projeto frontend demonstrativo de gestão de estoque, pronto para executar com Vite.

## Como executar

```bash
npm install
npm run dev
```

Abra o endereço exibido pelo Vite (normalmente `http://localhost:5173`).

## Login demonstrativo

- E-mail: `admin@stockos.com`
- Senha: `123456`

## Funcionalidades

- Login e logout
- Dashboard
- CRUD demonstrativo de produtos
- CRUD demonstrativo de categorias
- CRUD demonstrativo de fornecedores
- Entradas e saídas com validação de estoque insuficiente
- Tela de relatórios com indicadores consolidados
- Exportação do relatório de estoque em PDF
- Identificação fixa do administrador
- Persistência local com `localStorage`
- Layout responsivo

> Esta versão funciona sem backend para fins de demonstração. Quando a API Spring Boot estiver pronta, os dados locais podem ser substituídos por chamadas REST.
