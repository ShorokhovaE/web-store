package ru.geekbrains.store.core.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.OrderDto;
import ru.geekbrains.store.core.entities.Order;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderConverter {
    private final OrderItemConverter orderItemConverter;

    public OrderDto entityToDto(Order o) {
        return new OrderDto(o.getId(), o.getUsername(), o.getTotalPrice(), o.getItems().stream()
                .map(orderItemConverter::entityToDto).collect(Collectors.toList()));
    }
}
