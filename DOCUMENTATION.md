# 💡 Ferramenta: Gestor de Licenças de Software para Pequenas Empresas

## 🧩 O que é?
Um sistema onde pequenas empresas podem registrar os softwares que usam, com:
- licenças compradas
- validade
- quem é o responsável pelo software
- alertas de expiração de licenças
- histórico de renovação

## ⚙️ Funcionalidades

| Funcionalidade | Descrição |
|----------------|-----------|
| CRUD de softwares | Nome, versão, fornecedor, site |
| CRUD de licenças | Tipo (perpétua, anual), chave, validade, valor |
| Alertas | Licenças expirando em 30 dias |
| Dashboard | Quantidade por fornecedor, custo anual, licenças ativas |
| Upload de comprovantes/licenças | PDF/Imagem do contrato ou comprovante |
| Login com perfis | Admin (gerencia tudo), Usuário (só visualiza) |
| Histórico de renovações | Quando foi renovada, por quem, valor pago |

## 🧪 Tecnologias utilizadas

| Parte | Tecnologia |
|-------|---------|
| Backend | Java 21 + Spring Boot (Web, JPA, Security, Validation) |
| Banco de dados | PostgreSQL |
| Autenticação | Spring Security + JWT ou OAuth2 (Google login) |
| Storage | Salvar comprovantes no sistema de arquivos ou Amazon S3 |
| Tests | JUnit + Mockito + Testcontainers|
| Documentação | OpenAPI |
| DevOps | Docker, Docker Compose com Postgres, GitHub Actions |

## 🎲 Modelo de dados

### Software
| Campo | Tipo |
|-------|------|
| idSoftware | Long |
| descricao | String |
| versao | String |
| fornecedor | Fornecedor |
| urlSite | String |

### Licenca
| Campo | Tipo |
|-------|------|
| IdLicenca | Long |
| idSoftware | Sofware |
| recorrencia | Recorrencia |
| chave | String |
| dataAquisicao | LocalDateTime |
| dataVencimento | LocalDateTime |
| renovacao | Renovacao |
| comprovante | Comprovante |
| responsavel | Usuario |

### Usuario
| Campo | Tipo |
|-------|------|
| idPessoa | Long |
| nomeCompleto | String |
| dataNascimento | LocalDateTime |
| cpf | String |
| sexo | String |
| Contato | Contato |

### Renovacao
| Campo | Tipo |
|-------|------|
| idRenovacao | Long |
| solicitante | Usuario |
| dataSolicitaco | LocalDateTime |
| dataRenovacao | LocalDateTime |

### Recorrencia
| Campo | Tipo |
|-------|------|
| idRecorrencia | Long |
| descricao | String |

### Contato
| Campo | Tipo |
|-------|------|
| idContato | Long |
| descricao | String |
| tipoContato | TipoContato |
| valor | String |

### TipoContato
| Campo | Tipo |
|-------|------|
| idTipoContato | Long |
| descricao | String |

### Comprovante
| Campo | Tipo |
|-------|------|
| idComprovante | Long |
| nomeArquivo | String |
| conteudo | byte |
| tipoMidia | String |
| caminhoArquivo | String |
| dataUpload | LocalDateTime |