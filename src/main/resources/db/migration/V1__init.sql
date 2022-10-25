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

create table users
(
    id       int NOT NULL AUTO_INCREMENT,
    username varchar(45) NOT NULL,
    password varchar(100) NOT NULL,
    enable   tinyint(1),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id)
);

create table roles
(
    id   int NOT NULL AUTO_INCREMENT,
    name varchar(45) NOT NULL,
    PRIMARY KEY (id)
);

create table users_roles
(
    user_id int NOT NULL,
    role_id int NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roles (id)
);

create table orders
(
    id   int NOT NULL AUTO_INCREMENT,
    user_id int,
    total_price int not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES users (id)
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

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password)
values ('bob', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i'),
       ('john', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);



