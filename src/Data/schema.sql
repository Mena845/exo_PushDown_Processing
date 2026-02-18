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

-- insertion des donnees dans invoice_line
INSERT INTO invoice_line (invoice_id, label, quantity, unit_price) VALUES
                                                                       (1, 'Produit A', 2, 100.00),
                                                                       (1, 'Produit B', 1, 50.00),
                                                                       (2, 'Produit A', 5, 100.00),
                                                                       (2, 'Service C', 1, 200.00),
                                                                       (3, 'Produit B', 3, 50.00);