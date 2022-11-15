create table products
(
    id    int NOT NULL AUTO_INCREMENT,
    title varchar(45),
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

insert into products (title, price)
values ('Milk', 24),
       ('Bob', 34),
       ('Nuts', 134);



create table orders
(
    id   int NOT NULL AUTO_INCREMENT,
    username varchar(255),
    total_price int not null,
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
    price_per_product int,
    price int,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (order_id) REFERENCES orders (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
