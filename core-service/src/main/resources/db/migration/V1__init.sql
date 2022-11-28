create table products
(
    id    int NOT NULL AUTO_INCREMENT,
    title varchar(45),
    price decimal(5,2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

insert into products (title, price)
values ('Milk', 24.50),
       ('Bobs', 15.50),
       ('Bread', 22.00),
       ('Water', 30.50),
       ('Candy', 100.00),
       ('Cheese', 250.50),
       ('Milk1', 24.50),
       ('Bobs1', 15.50),
       ('Bread1', 22.00),
       ('Water1', 30.50),
       ('Candy1', 100.00),
       ('Cheese1', 250.50),
       ('Milk2', 24.50),
       ('Bobs2', 15.50),
       ('Bread2', 22.00),
       ('Water2', 30.50),
       ('Candy2', 100.00),
       ('Cheese2', 250.50),
       ('Nuts', 150.00);



create table orders
(
    id   int NOT NULL AUTO_INCREMENT,
    username varchar(255),
    total_price decimal(5,2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);


create table order_items
(
    id   int NOT NULL AUTO_INCREMENT,
    product_id int,
    order_id int,
    quantity int,
    price_per_product decimal(5,2),
    price decimal(5,2),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
