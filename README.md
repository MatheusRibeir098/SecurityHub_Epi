# 🚀 SecurityHub EPI

## Gerenciamento de Equipamentos de Proteção Individual 🛡️

Bem-vindo ao repositório do projeto **SecurityHub EPI**! Este sistema visa simplificar o gerenciamento de Equipamentos de Proteção Individual (EPIs) em um ambiente corporativo ou institucional, garantindo que os usuários tenham acesso facilitado aos equipamentos necessários e que o controle de estoque seja eficiente.

---

## Visão Geral do Projeto 💡

O **SecurityHub EPI** é uma aplicação web robusta projetada para gerenciar o ciclo de vida dos EPIs.

**Objetivos Principais:**

* **Para Administradores:** Cadastrar novos EPIs ➕, gerenciar o estoque existente 📦 e controlar os usuários do sistema 👥.
* **Para Usuários:** Visualizar EPIs disponíveis 👀, solicitar empréstimo de equipamentos 🤝 e registrar a devolução ↩️.

---

## Tecnologias Utilizadas 🛠️

Este projeto foi construído utilizando as seguintes tecnologias:

* **Backend:** Spring Boot (Java) ☕
* **Frontend:** HTML 🌐, CSS 🎨, JavaScript (com Thymeleaf para renderização de templates) ✨
* **Banco de Dados:** MySQL (manipulado via SQL Workbench) 🗄️

---

## Estado Atual do Desenvolvimento 🚧

Atualmente, estamos focados no desenvolvimento das funcionalidades voltadas para o **administrador** ⚙️. Isso inclui:

* **Estrutura da Aplicação:** A base da aplicação Spring Boot está configurada, com a estrutura de pastas para templates Thymeleaf e recursos estáticos devidamente organizada.
* **Segurança (Spring Security):** Implementação funcional de login 🔐 e logout 🚪. A maioria das páginas exige autenticação, e a autenticação de usuários é feita diretamente do banco de dados MySQL com senhas BCrypt.
* **Gerenciamento de EPIs:**
    * Entidade `EPI.java` e `EpiRepository` definidos.
    * **Funcionalidades de CRUD (Create, Read, Update, Delete) em andamento para EPIs:**
        * Formulário de cadastro de EPIs (`/epis/cadastrar-epis`) 📝.
        * Processamento do cadastro de EPIs no backend.
        * **Página de Listagem de EPIs (`/epis/listar`):** Exibe todos os EPIs cadastrados em uma tabela 📊.
        * **Funcionalidade de Exclusão de EPIs:** Permite que administradores removam EPIs da lista 🗑️.

**Próximos Passos:**

Após consolidarmos as funcionalidades do administrador, o foco será expandido para as funcionalidades do **usuário** 🧑‍💻, incluindo:

* Visualização de EPIs disponíveis para empréstimo.
* Registro de empréstimos de EPIs.
* Registro de devoluções de EPIs.

---

## Colaboradores 👋

Este projeto está sendo desenvolvido com dedicação por:

* **Matheus Ribeiro:** Foco principal no desenvolvimento do **Backend** da aplicação 💻.
* **Eduardo:** Foco principal no desenvolvimento do **Frontend** (HTML, CSS, JavaScript) 🎨.
* **Paulo:** Foco principal no desenvolvimento do **Frontend** (HTML, CSS, JavaScript) 🌐.
