-- Migration para implementar delete lógico em todas as tabelas

-- Adiciona a coluna is_deleted em cada tabela com valor padrão como false
ALTER TABLE supplier ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

ALTER TABLE software ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

ALTER TABLE users ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

ALTER TABLE renewal ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

ALTER TABLE proof ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

ALTER TABLE license ADD COLUMN is_deleted BOOLEAN DEFAULT false NOT NULL;

-- Cria índices sobre a coluna is_deleted para melhorar a performance em consultas
CREATE INDEX idx_supplier_is_deleted ON supplier (is_deleted);
CREATE INDEX idx_software_is_deleted ON software (is_deleted);
CREATE INDEX idx_users_is_deleted ON users (is_deleted);
CREATE INDEX idx_renewal_is_deleted ON renewal (is_deleted);
CREATE INDEX idx_proof_is_deleted ON proof (is_deleted);
CREATE INDEX idx_license_is_deleted ON license (is_deleted);