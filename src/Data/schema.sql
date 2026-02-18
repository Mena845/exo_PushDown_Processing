-- creation de la database
create database  pushdownprocessing;

-- cretaion de la type enum
CREATE TYPE invoice_status AS ENUM ('DRAFT', 'CONFIRMED', 'PAID');

-- creation de la table invoice
CREATE TABLE invoice (
                         id SERIAL PRIMARY KEY,
                         customer_name VARCHAR(255) NOT NULL,
                         status invoice_status NOT NULL
);

