-- Script: BaseDatos.sql
-- Crea tablas y datos de ejemplo para la prueba técnica

-- Clientes
CREATE TABLE IF NOT EXISTS tbl_clients (
  id SERIAL PRIMARY KEY,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  address VARCHAR(255),
  phone VARCHAR(50),
  client_id VARCHAR(100) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  status BOOLEAN DEFAULT TRUE
);

-- Cuentas
CREATE TABLE IF NOT EXISTS tbl_accounts (
  id SERIAL PRIMARY KEY,
  account_number VARCHAR(50) UNIQUE NOT NULL,
  balance NUMERIC(19,2) NOT NULL DEFAULT 0,
  initial_balance NUMERIC(19,2) NOT NULL DEFAULT 0,
  account_type VARCHAR(50),
  status BOOLEAN DEFAULT TRUE,
  owner VARCHAR(200)
);

-- Movimientos
CREATE TABLE IF NOT EXISTS tbl_movements (
  id SERIAL PRIMARY KEY,
  date TIMESTAMP NOT NULL DEFAULT now(),
  movement_type VARCHAR(100) NOT NULL,
  amount NUMERIC(19,2) NOT NULL DEFAULT 0,
  balance NUMERIC(19,2) NOT NULL DEFAULT 0,
  account_id INTEGER NOT NULL REFERENCES tbl_accounts(id) ON DELETE CASCADE
);

-- Datos de ejemplo: Clientes
INSERT INTO tbl_clients (first_name, last_name, address, phone, client_id, password, status) VALUES
('Jose', 'Lema', 'Otavalo sn y principal', '098254785', 'jose.lema', '1234', true),
('Marianela', 'Montalvo', 'Amazonas y NNUU', '097548965', 'marianela.m', '5678', true),
('Juan', 'Osorio', '13 junio y Equinoccial', '098874587', 'juan.osorio', '1245', true)
ON CONFLICT DO NOTHING;

-- Datos de ejemplo: Cuentas
INSERT INTO tbl_accounts (account_number, initial_balance, balance, account_type, status, owner) VALUES
('478758', 2000, 2000, 'Ahorro', true, 'Jose Lema'),
('225487', 100, 100, 'Corriente', true, 'Marianela Montalvo'),
('495878', 0, 0, 'Ahorros', true, 'Juan Osorio'),
('496825', 540, 540, 'Ahorros', true, 'Marianela Montalvo')
ON CONFLICT DO NOTHING;

-- Movimientos de ejemplo (fechas ejemplo)
INSERT INTO tbl_movements (date, movement_type, amount, balance, account_id)
SELECT now(), 'Retiro', -575, 1425, id FROM tbl_accounts WHERE account_number = '478758' LIMIT 1;

INSERT INTO tbl_movements (date, movement_type, amount, balance, account_id)
SELECT now(), 'Deposito', 600, 700, id FROM tbl_accounts WHERE account_number = '225487' LIMIT 1;

INSERT INTO tbl_movements (date, movement_type, amount, balance, account_id)
SELECT now(), 'Deposito', 150, 150, id FROM tbl_accounts WHERE account_number = '495878' LIMIT 1;

INSERT INTO tbl_movements (date, movement_type, amount, balance, account_id)
SELECT now(), 'Retiro', -540, 0, id FROM tbl_accounts WHERE account_number = '496825' LIMIT 1;

-- Fin del script
-- ==========================================================
-- SECTION: CUSTOMER & PERSON (ms-customer-service)
-- ==========================================================

CREATE TABLE tbl_persons (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    gender VARCHAR(20),
    age INT,
    identification VARCHAR(20) UNIQUE NOT NULL, -- [cite: 28, 29]
    address VARCHAR(255),
    phone VARCHAR(20)
);

CREATE TABLE tbl_clients (
    customer_id SERIAL PRIMARY KEY, -- [cite: 32, 33]
    password VARCHAR(255) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    person_id INT REFERENCES tbl_persons(id) -- 
);

-- ==========================================================
-- SECTION: ACCOUNT & MOVEMENTS (ms-account-service)
-- ==========================================================

CREATE TABLE tbl_accounts (
    id SERIAL PRIMARY KEY,
    account_number VARCHAR(20) UNIQUE NOT NULL, -- [cite: 36, 37]
    account_type VARCHAR(20) NOT NULL,
    initial_balance DECIMAL(12,2) NOT NULL,
    status BOOLEAN DEFAULT TRUE,
    customer_id INT NOT NULL 
);

CREATE TABLE tbl_movements (
    id SERIAL PRIMARY KEY, -- [cite: 42]
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- [cite: 41]
    movement_type VARCHAR(50) NOT NULL,
    value DECIMAL(12,2) NOT NULL,
    balance DECIMAL(12,2) NOT NULL,
    account_id INT REFERENCES tbl_accounts(id)
);