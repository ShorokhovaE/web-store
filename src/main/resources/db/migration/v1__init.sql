create table products (
                          id INT NOT NULL AUTO_INCREMENT,
                          title VARCHAR(45) NOT NULL,
                          cost INT NOT NULL,
                          PRIMARY KEY ('id')
                      );

create table users (
                        id INT NOT NULL AUTO_INCREMENT,
                        name VARCHAR(45),
                        password VARCHAR(100),
                        enable TINYINT(1),
                        PRIMARY KEY ('id')
                   );

create table user_product (
                             user_name VARCHAR(45),
                             product_id int NOT NULL,
                             PRIMARY KEY (user_name, product_id),
                             FOREIGN KEY (user_name) REFERENCES users(name),
                             FOREIGN KEY (product_id) REFERENCES products(id)
                          );

create table authorization (
                             user_name VARCHAR(45),
                             role VARCHAR(30),
                             FOREIGN KEY (user_name) REFERENCES  users(name)
                           );


