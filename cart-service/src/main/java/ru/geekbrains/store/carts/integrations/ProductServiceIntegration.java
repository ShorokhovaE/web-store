package ru.geekbrains.store.carts.integrations;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.client.RestTemplate;
import ru.geekbrains.store.api.ProductDto;

import java.util.Optional;

@Component
@AllArgsConstructor
public class ProductServiceIntegration {


    private final RestTemplate restTemplate;

    public Optional<ProductDto> getProductById(Long id) {
        return Optional.ofNullable(restTemplate.getForObject("http://localhost:8189/store/api/v1/products/" + id, ProductDto.class));
    }


}
