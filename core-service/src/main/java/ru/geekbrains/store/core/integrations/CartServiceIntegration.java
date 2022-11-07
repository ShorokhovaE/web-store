package ru.geekbrains.store.core.integrations;


import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.store.api.CartDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CartServiceIntegration {

    private final RestTemplate restTemplate;

    public Optional<CartDto> getCurrentCart() {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8190/store-carts/api/v1/cart", CartDto.class));
    }

    public void clear() {
        restTemplate.getForObject("http://localhost:8190/store-carts/api/v1/cart/clear", CartDto.class);
    }


}
