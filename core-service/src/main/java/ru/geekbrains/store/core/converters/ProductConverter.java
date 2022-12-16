package ru.geekbrains.store.core.converters;

import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.core.entities.Product;

@Component
public class ProductConverter {
    public ProductDto entityToDto(Product p) {

        return ProductDto.builder()
                .id(p.getId())
                .title(p.getTitle())
                .price(p.getPrice())
                .build();
    }
}
