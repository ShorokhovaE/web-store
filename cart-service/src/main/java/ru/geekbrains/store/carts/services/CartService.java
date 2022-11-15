package ru.geekbrains.store.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.api.ResourceNotFoundException;
import ru.geekbrains.store.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.store.carts.model.Cart;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        tempCart.add(product);
    }

    public void remove(Long productId) {
        productServiceIntegration.getProductById(productId);
        tempCart.remove(productId);
    }

    public void clear() {
        tempCart.clear();
    }

    public void removeItem(Long productId) {
        productServiceIntegration.getProductById(productId);
        tempCart.removeItem(productId);
    }
}
