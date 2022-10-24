package ru.geekbrains.spring.webstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.webstore.dtos.Cart;
import ru.geekbrains.spring.webstore.services.CartService;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id) {
        cartService.add(id);
    }

    @GetMapping ("/createOrder")
    public void createNewOrder() {
        cartService.createNewOrder();
    }

    @GetMapping
    public Cart getCurrentCart() {
        return cartService.getCurrentCart();
    }

    @GetMapping("/remove/{id}")
    public void removeFromCart(@PathVariable Long id) {
        cartService.remove(id);
    }

    @GetMapping("/clear")
    public void clearCart(){
        cartService.clear();
    }


}
