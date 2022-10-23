package ru.geekbrains.spring.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.spring.webstore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
