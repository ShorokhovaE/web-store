package ru.geekbrains.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.store.core.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
