package ru.geekbrains.store.carts.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.CartItemDto;
import ru.geekbrains.store.carts.model.CartItem;

@Component
public class CartItemConverter {

    public CartItemDto entityToDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setPrice(cartItem.getPrice());
        cartItemDto.setPricePerProduct(cartItem.getPricePerProduct());
        cartItemDto.setProductId(cartItem.getProductId());
        cartItemDto.setQuantity(cartItem.getQuantity());
        cartItemDto.setProductTitle(cartItem.getProductTitle());
        return cartItemDto;
    }
}
