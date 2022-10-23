package ru.geekbrains.spring.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.webstore.entities.Order;
import ru.geekbrains.spring.webstore.entities.Product;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
