package ru.geekbrains.store.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.api.ResourceNotFoundException;
import ru.geekbrains.store.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.store.carts.model.Cart;

import javax.annotation.PostConstruct;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductServiceIntegration productServiceIntegration;
    private HashMap<String, Cart> carts;

    @PostConstruct
    public void init() {
        carts = new HashMap<>();
    }

    public Cart getCurrentCart(String cartId) {
        if(!carts.containsKey(cartId)){
            carts.put(cartId, new Cart());
        }
        return carts.get(cartId);
    }

    public void add(String cartId, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        if(!carts.containsKey(cartId)){
            carts.put(cartId, new Cart());
        }
        carts.get(cartId).add(product);
    }

    public void remove(String cartId, Long productId) {
        productServiceIntegration.getProductById(productId);
        carts.get(cartId).remove(productId);
    }

    public void clear(String cartId) {
        carts.get(cartId).clear();
    }

    public void removeItem(String cartId, Long productId) {
        productServiceIntegration.getProductById(productId);
        carts.get(cartId).removeItem(productId);
    }
}
