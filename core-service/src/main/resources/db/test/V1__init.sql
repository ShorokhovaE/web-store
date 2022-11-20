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
       ('Bob', 34.50),
       ('Nuts', 150.00),
       ('Bread', 200.00);



create table orders
(
    id   int NOT NULL AUTO_INCREMENT,
    username varchar(255),
    total_price decimal(5,2) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

insert into orders (username, total_price)
values ('Bob', 600.50),
       ('Bob', 125.50),
       ('Mila', 150.00),
       ('Tony', 200.00),
       ('Billy', 200.00);



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
