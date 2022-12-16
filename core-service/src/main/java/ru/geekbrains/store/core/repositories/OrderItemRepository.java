package ru.geekbrains.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.store.core.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
