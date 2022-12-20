package ru.geekbrains.store.carts.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.carts.integrations.ProductServiceIntegration;
import ru.geekbrains.store.carts.model.Cart;
import ru.geekbrains.store.carts.observer.Observed;
import ru.geekbrains.store.carts.observer.Observer;

import java.util.function.Consumer;

@Service
@RequiredArgsConstructor
public class CartService implements Observed {

    private final Observer observer;

    private final ProductServiceIntegration productServiceIntegration;
    private final RedisTemplate<String, Object> redisTemplate;
    @Value("${cart-service.cart-prefix}")
    private String cartPrefix;

    public Cart getCurrentCart(String uuid) {
        String targetUuid = cartPrefix + uuid;
        if (!redisTemplate.hasKey(targetUuid)) {
            redisTemplate.opsForValue().set(targetUuid, new Cart());
        }
        return (Cart) redisTemplate.opsForValue().get(targetUuid);
    }

    public void add(String uuid, Long productId) {
        ProductDto product = productServiceIntegration.getProductById(productId);
        execute(uuid, cart -> cart.add(product));
        Cart cart = getCurrentCart(uuid);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
        observer.handleEvent("В корзину добавили " + product.getTitle());
    }

    public void remove(String uuid, Long productId) {
        execute(uuid, cart -> cart.remove(productId));
        observer.handleEvent("Из корзины убрали 1 единицу товара: " + productServiceIntegration.getProductById(productId).getTitle());
    }

    public void clear(String uuid) {
        execute(uuid, cart -> cart.clear());
        observer.handleEvent("Корзину очистили");
    }

    public void removeItem(String uuid, Long productId) {
        execute(uuid, cart -> cart.removeItem(productId));
        observer.handleEvent("Из корзины убрали товар: " + productServiceIntegration.getProductById(productId).getTitle());
    }

    private void execute(String uuid, Consumer<Cart> operation) {
        Cart cart = getCurrentCart(uuid);
        operation.accept(cart);
        redisTemplate.opsForValue().set(cartPrefix + uuid, cart);
    }
}
