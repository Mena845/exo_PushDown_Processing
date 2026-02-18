-- creation de la database
create database  pushdownprocessing;

-- cretaion de la type enum
CREATE TYPE invoice_status AS ENUM ('DRAFT', 'CONFIRMED', 'PAID');