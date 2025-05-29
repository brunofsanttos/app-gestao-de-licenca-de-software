# 💼 Gestor de Licenças de Software

## 📌 Descrição

**Gestor de Licenças de Software** é uma aplicação web desenvolvida em **Java com Spring Boot** e **PostgreSQL** que permite a pequenas empresas gerenciarem os softwares utilizados internamente, suas licenças, validade, renovações e custos.

O objetivo do projeto é oferecer um sistema simples, seguro e útil para manter o controle das licenças de software utilizadas por uma organização, evitando o uso indevido, vencimentos inesperados ou desperdício financeiro.

---

## 🎯 Funcionalidades

- ✅ Cadastro de softwares: nome, versão, fornecedor, site
- ✅ Registro de licenças: tipo (anual, perpétua), chave, validade, valor
- ✅ Alertas de expiração de licenças (ex: expiram em até 30 dias)
- ✅ Histórico de renovações com data, valor e responsável
- ✅ Dashboard com métricas e gráficos (opcional)
- ✅ Upload de comprovantes e documentos (PDF ou imagens)
- ✅ Autenticação com perfis: admin (gerencia) e usuário (visualiza)

---

## 🧰 Tecnologias Utilizadas

| Camada | Tecnologia |
|--------|------------|
| Linguagem | Java 21 |
| Backend | Spring Boot (Web, JPA, Security, Validation) |
| Banco de dados | PostgreSQL |
| Autenticação | Spring Security + JWT ou OAuth2 (ex: login com Google) |
| Armazenamento de arquivos | Sistema local ou Amazon S3 |
| Testes | JUnit 5 + Mockito + Testcontainers |
| Documentação da API | Swagger / OpenAPI |
| DevOps (opcional) | Docker + Docker Compose + GitHub Actions |

---

## 🗃️ Modelo de Dados (resumido)

- **Software**: nome, versão, fornecedor
- **Licença**: tipo, chave, validade, custo, vínculo com um software
- **Usuário**: nome, e-mail, perfil (ADMIN / USER)
- **Renovação**: data, valor, responsável
- **Comprovantes**: arquivo associado a uma licença

---

## 🚀 Possibilidades futuras

- Envio de notificações por e-mail
- Exportação de relatórios em PDF/Excel
- Integração com APIs de licença (Microsoft, JetBrains, etc.)
- Multiempresa e multiusuário com plano gratuito/pago (SaaS)

---

## 👨‍💻 Propósito do projeto

Este projeto foi criado com foco em:

- Consolidar conhecimentos em desenvolvimento com Spring Boot
- Praticar modelagem relacional com PostgreSQL
- Aplicar boas práticas de autenticação e segurança
- Demonstrar domínio de arquitetura web moderna em Java
- Servir como projeto de portfólio profissional

---

## 📁 Como rodar o projeto

<em>Instruções de setup com Maven, Docker, ou IDE, devem ser adicionadas aqui após implementação.</em>

---

## 📄 Licença

Projeto de código aberto sob a licença MIT.
