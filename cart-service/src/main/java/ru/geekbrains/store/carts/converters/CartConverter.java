package ru.geekbrains.store.carts.converters;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.carts.model.Cart;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartConverter {

    private final CartItemConverter cartItemConverter;

    public CartDto entityToDto(Cart cart) {

        return CartDto.builder()
                .totalPrice(cart.getTotalPrice())
                .items(cart.getItems().stream().map(cartItemConverter::entityToDto).collect(Collectors.toList()))
                .build();
    }
}
