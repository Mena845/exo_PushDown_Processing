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

-- creation de la table invoice_line
CREATE TABLE invoice_line (
                              id SERIAL PRIMARY KEY,
                              invoice_id INT NOT NULL REFERENCES invoice(id) ON DELETE CASCADE,
                              label VARCHAR(255) NOT NULL,
                              quantity INT NOT NULL CHECK (quantity > 0),
                              unit_price NUMERIC(10,2) NOT NULL CHECK (unit_price >= 0)
);

-- creation de la table tax_config
CREATE TABLE tax_config (
                            id SERIAL PRIMARY KEY,
                            label VARCHAR(255) NOT NULL,
                            rate NUMERIC(5,2) NOT NULL CHECK (rate >= 0)
);

-- insertion des donnes dans invoice
INSERT INTO invoice (customer_name, status) VALUES
                                                ('Alice', 'CONFIRMED'),
                                                ('Bob', 'PAID'),
                                                ('Charlie', 'DRAFT');
