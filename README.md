# 🚀 SecurityHub EPI

## Sistema Completo de Gerenciamento de Equipamentos de Proteção Individual 🛡️

Bem-vindo ao repositório do projeto **SecurityHub EPI**! Este sistema foi desenvolvido para simplificar e otimizar o gerenciamento de Equipamentos de Proteção Individual (EPIs) em ambientes corporativos ou institucionais. Ele garante que os usuários tenham acesso facilitado aos equipamentos necessários, ao mesmo tempo em que oferece um controle de estoque eficiente e um gerenciamento robusto de usuários.

---

## Visão Geral do Projeto 💡

O **SecurityHub EPI** é uma aplicação web completa, projetada para gerenciar o ciclo de vida dos EPIs de forma integrada.

**Funcionalidades Principais:**

* **Para Administradores:**
    * **Cadastro de EPIs:** Adicionar novos EPIs ao sistema.
    * **Listagem e Edição de EPIs:** Visualizar todos os EPIs cadastrados em uma tabela, com opções para editar suas informações (nome e quantidade).
    * **Exclusão de EPIs:** Remover EPIs da lista.
    * **Gerenciamento de Usuários:** Cadastrar, listar, editar e excluir usuários do sistema, incluindo a definição de seus perfis (ADMIN/USUARIO).
* **Para Usuários (Funcionalidades de Usuário Autenticado):**
    * **Visualização de EPIs Disponíveis:** Acessar uma lista de EPIs com quantidade disponível para empréstimo.
    * **Solicitação de Empréstimo:** Realizar o empréstimo de EPIs disponíveis, que automaticamente atualiza a quantidade em estoque.
    * **Visualização de Empréstimos Pendentes:** Acompanhar a lista de EPIs emprestados que ainda não foram devolvidos.
    * **Registro de Devolução:** Registrar a devolução de um EPI, incrementando novamente a quantidade em estoque.
    * **Autenticação:** Sistema de login e logout robusto com Spring Security.

---

## Tecnologias Utilizadas 🛠️

Este projeto foi construído utilizando as seguintes tecnologias, garantindo uma arquitetura moderna e escalável:

* **Backend:** Spring Boot (Java 17)
* **Segurança:** Spring Security (com autenticação via banco de dados e senhas BCrypt)
* **Persistência:** Spring Data JPA
* **Banco de Dados:** MySQL (com dump de teste fornecido)
* **Frontend:** HTML, CSS, JavaScript (com Thymeleaf para renderização de templates dinâmicos)
* **Ferramentas de Build:** Maven

---

## Estado do Projeto ✅

O projeto **SecurityHub EPI** está **FINALIZADO**. Todas as funcionalidades planejadas para o gerenciamento de EPIs e usuários, tanto para o perfil de administrador quanto para o de usuário, foram implementadas e testadas. A aplicação oferece uma solução completa para o controle de equipamentos de proteção individual.

---

## Colaboradores 👋

Este projeto foi desenvolvido com dedicação por:

* **Matheus Ribeiro:** Desenvolvimento principal do **Backend** da aplicação.
* **Eduardo:** Desenvolvimento principal do **Frontend** (HTML, CSS, JavaScript).
* **Paulo:** Desenvolvimento principal do **Frontend** (HTML, CSS, JavaScript).
