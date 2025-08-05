# ERP de Obras - Cora Duarte

Sistema ERP Web desenvolvido para o gerenciamento de obras e processos administrativos relacionados.

![Java](https://img.shields.io/badge/Backend-Java%2021%20%7C%20SpringBoot-blue)
![Frontend](https://img.shields.io/badge/Frontend-Vue.js%203-green)
![Banco de Dados](https://img.shields.io/badge/Database-MySQL-orange)

---

## 📦 Tecnologias Utilizadas

### Backend
- Java 21
- Spring Boot 3.4.1
- Spring Security
- Spring Data JPA
- HikariCP
- MySQL
- Lombok
- JWT para autenticação

### Frontend
- Vue.js 3 (CLI)
- Axios
- Vue Router
- Bootstrap / Tailwind (opcional, caso esteja usando)

### Outros
- Maven (empacotamento)
- NGINX (proxy reverso)
- Certbot / Let's Encrypt (HTTPS)
- VPS Linux (produção)

---

## 🔧 Funcionalidades

- 📋 Cadastro e edição de clientes
- 🏗️ Gerenciamento de etapas da obra
- 🧾 Cálculo automático de preço da etapa
- 🧰 Associação de itens a etapas
- 🔐 Autenticação JWT
- 📊 Dashboard (em desenvolvimento)

---

## 🚀 Executando Localmente

### Pré-requisitos
- Java 21
- Maven
- Node.js + npm
- MySQL

### Backend

```bash
cd backend/erp
./mvnw spring-boot:run
