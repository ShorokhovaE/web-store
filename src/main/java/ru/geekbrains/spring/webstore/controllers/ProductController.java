package ru.geekbrains.spring.webstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.webstore.dtos.CreateNewProductDto;
import ru.geekbrains.spring.webstore.dtos.ProductDto;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.exceptions.AppError;
import ru.geekbrains.spring.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.webstore.services.ProductService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<ProductDto> findAllProducts() {
        return productService.findAll()
                .stream()
                .map(p -> new ProductDto(p.getId(), p.getTitle(), p.getPrice()))
                .collect(Collectors.toList());
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
