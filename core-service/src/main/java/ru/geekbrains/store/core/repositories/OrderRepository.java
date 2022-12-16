package ru.geekbrains.store.core.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.geekbrains.store.core.entities.Order;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUsername(String username);
}
