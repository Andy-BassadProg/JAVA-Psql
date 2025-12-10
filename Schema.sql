CREATE TABLE products (
    id int,
    name varchar(100),
    price decimal (6,2),
    creation_datetime timestamp 
);

CREATE TABLE product_category (
    id int,
    name varchar,
    product_id int, FOREIGN KEY (product_id) REFERENCES products(id)
);


