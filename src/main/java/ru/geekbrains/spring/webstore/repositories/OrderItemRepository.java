package ru.geekbrains.spring.webstore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.spring.webstore.entities.Order;
import ru.geekbrains.spring.webstore.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
