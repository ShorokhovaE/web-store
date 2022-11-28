package ru.geekbrains.store.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.OrderItemDto;
import ru.geekbrains.store.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem o) {
        return new OrderItemDto(o.getProduct().getId(), o.getProduct().getTitle(), o.getQuantity(), o.getPricePerProduct(), o.getPrice());
    }
}
