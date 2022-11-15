package ru.geekbrains.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.CreateNewProductDto;
import ru.geekbrains.store.api.ProductDto;
import ru.geekbrains.store.api.ResourceNotFoundException;
import ru.geekbrains.store.core.services.ProductService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        List<ProductDto> productDtos = productService.findAll()
                .stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
                .collect(Collectors.toList());
        return productDtos;
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
