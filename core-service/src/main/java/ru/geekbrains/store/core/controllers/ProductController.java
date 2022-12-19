package ru.geekbrains.store.core.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.*;
import ru.geekbrains.store.core.converters.ProductConverter;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.proxy.ProductProxy;
import ru.geekbrains.store.core.services.ProductService;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@Tag(name="Продукты", description = "Методы работы с продуктами")
public class ProductController {

    private final ProductService productService;
    private final ProductProxy productProxy;
    private final ProductConverter productConverter;

    @Operation(
            summary = "Запрос на получение отфильтрованного списка продуктов",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PageDto.class))
                    )
            }
    )
    @GetMapping
    public PageDto<ProductDto> findAllProducts(
            @RequestParam(defaultValue = "1", name = "p") Integer page,
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "min_price") Integer minPrice,
            @RequestParam(required = false, name = "max_price") Integer maxPrice
    ) {

        if (page < 1) {
            page = 1;
        }
        Specification<Product> spec = productService.createSpecByFilters(minPrice, maxPrice, title);

        Page<ProductDto> jpaPage = productProxy.findAll(spec, page - 1).map(productConverter::entityToDto);

        PageDto<ProductDto> out = new PageDto<>();
        out.setPage(jpaPage.getNumber());
        out.setItems(jpaPage.getContent());
        out.setTotalPages(jpaPage.getTotalPages());
        return out;
    }

    @Operation(
            summary = "Запрос на получение продукта по id",
            responses = {
                    @ApiResponse(
                            description = "Успешный ответ", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    ),
                    @ApiResponse(
                            description = "Продукт не найден", responseCode = "404",
                            content = @Content(schema = @Schema(implementation = AppError.class))
                    )
            }
    )
    @GetMapping("/{id}")
    public ProductDto findById(@PathVariable @Parameter(description = "Идентификатор продукта", required = true) Long id) {
        return productService.findById(id)
                .map(p -> ProductDto.builder().id(p.getId()).title(p.getTitle()).price(p.getPrice()).build())
                .orElseThrow(() -> new ResourceNotFoundException("Продукт не найден, id: " + id));

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }

    @Operation(
            summary = "Запрос на создание нового продукта",
            responses = {
                    @ApiResponse(
                            description = "Продукт успешно создан", responseCode = "201",
                            content = @Content(schema = @Schema(implementation = ProductDto.class))
                    )
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createNewProducts(@RequestBody CreateNewProductDto createNewProductDto) {
        productService.createNewProduct(createNewProductDto);
    }
}
