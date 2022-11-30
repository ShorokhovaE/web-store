package ru.geekbrains.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.CreateNewProductDto;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.api.ResourceNotFoundException;
import ru.geekbrains.store.core.converters.ProductConverter;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.services.ProductService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductConverter productConverter;


    @GetMapping
    public List<ProductDto> findAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice
    ) {

        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);
        return productService.findAll(spec, page - 1).map(productConverter::entityToDto).getContent();
    }

    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable Long id) {
        return productService.findById(id)
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody CreateNewProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }


}
