package ru.geekbrains.store.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.CartItemDto;
import ru.geekbrains.store.carts.model.CartItem;

import java.math.BigDecimal;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {

        return CartItemDto.builder()
                .productTitle(cartItem.getProductTitle())
                .pricePerProduct(cartItem.getPricePerProduct())
                .quantity(cartItem.getQuantity())
                .price(cartItem.getPrice())
                .productId(cartItem.getProductId())
                .build();

    }
}
