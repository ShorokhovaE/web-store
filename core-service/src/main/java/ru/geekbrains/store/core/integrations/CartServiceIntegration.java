package ru.geekbrains.store.core.integrations;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import ru.geekbrains.store.api.CartDto;

@Component
@AllArgsConstructor
public class CartServiceIntegration {

    private final WebClient cartServiceWebClient;

    public CartDto getCurrentCart(String username) {

        return cartServiceWebClient.get()
                .uri("/api/v1/cart/" + username)
                .retrieve()
                .bodyToMono(CartDto.class)
                .block();
    }

    public void clear(String username) {
        cartServiceWebClient.get()
                .uri("/api/v1/cart/clear" + username)
                .retrieve()
                .toBodilessEntity()
                .block();
    }
}
