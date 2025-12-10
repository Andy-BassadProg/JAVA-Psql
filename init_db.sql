-- Création de la base de données
CREATE DATABASE product_management_db;

-- Connexion à la base de données 
\c product_management_db;

-- Création de l'utilisateur avec mot de passe
CREATE USER product_manager_user WITH PASSWORD '123456';

-- Attribution des privilèges sur la base de données
GRANT CONNECT ON DATABASE product_management_db TO product_manager_user;


-- Autoriser la création de schémas et tables
GRANT CREATE ON SCHEMA public TO product_manager_user;

-- Donner tous les droits CRUD sur les tables
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO product_manager_user;

-- Donner les droits sur les séquences 
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO product_manager_user;

-- Inserer les donnes dans une table 
INSERT INTO products (name, price) VALUES ('Laptop', 1200.00);

-- voir les elements dans une table 
INSERT INTO products (name, price) VALUES ('Laptop', 1200.00);

-- Mettre a jour les donnes dans une table
UPDATE products SET price = 1100.00 WHERE name = 'Laptop';

--Supprimer les donnnes dans une table 
DELETE FROM products WHERE name = 'Laptop';