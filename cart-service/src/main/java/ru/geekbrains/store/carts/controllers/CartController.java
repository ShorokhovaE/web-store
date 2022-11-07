package ru.geekbrains.store.carts.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.carts.converters.CartConverter;
import ru.geekbrains.store.carts.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CartController {

    private final CartService cartService;
    private final CartConverter cartConverter;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping
    public CartDto getCurrentCart() {
        return cartConverter.entityToDto(cartService.getCurrentCart());
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }

    @GetMapping("/delete/{id}")
    public void removeItem(@PathVariable Long id){
        cartService.removeItem(id);
    }


}
