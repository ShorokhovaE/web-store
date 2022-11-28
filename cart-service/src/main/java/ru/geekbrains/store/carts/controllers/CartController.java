package ru.geekbrains.store.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.api.StringResponse;
import ru.geekbrains.store.carts.converters.CartConverter;
import ru.geekbrains.store.carts.services.CartService;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/generate_id")
    public StringResponse generateGuestCartId() {
        return new StringResponse(UUID.randomUUID().toString());
    }

    @GetMapping("/{guestCartId}")
    public CartDto getCurrentCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String guestCartId) {
        String currentCartId = selectCartId(username, guestCartId);
        return cartConverter.entityToDto(cartService.getCurrentCart(currentCartId));
    }
    @GetMapping("/{guestCartId}/add/{id}")
    public void addToCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String guestCartId, @PathVariable Long id) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.add(currentCartId, id);
    }

    @GetMapping("/{guestCartId}/remove/{id}")
    public void removeFromCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String guestCartId, @PathVariable Long id) {
        String currentCartId = selectCartId(username, guestCartId);
        cartService.remove(currentCartId, id);
    }

    @GetMapping("/{guestCartId}/clear")
    public void clearCart(@RequestHeader(name = "username", required = false) String username, @PathVariable String guestCartId){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.clear(currentCartId);
    }

    @GetMapping("/{guestCartId}/delete/{id}")
    public void removeItem(@RequestHeader(name = "username", required = false) String username, @PathVariable String guestCartId, @PathVariable Long id){
        String currentCartId = selectCartId(username, guestCartId);
        cartService.removeItem(currentCartId, id);
    }

    private String selectCartId(String username, String guestCartId) {
        if (username != null) {
            return username;
        }
        return guestCartId;
    }

}
