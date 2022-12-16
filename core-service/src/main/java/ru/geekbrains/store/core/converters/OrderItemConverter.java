package ru.geekbrains.store.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.OrderItemDto;
import ru.geekbrains.store.core.entities.OrderItem;

@Component
public class OrderItemConverter {
    public OrderItemDto entityToDto(OrderItem orderItem) {

        return OrderItemDto.builder()
                .id(orderItem.getId())
                .price(orderItem.getPrice())
                .pricePerProduct(orderItem.getPricePerProduct())
                .productTitle(orderItem.getProduct().getTitle())
                .quantity(orderItem.getQuantity())
                .build();

    }
}
