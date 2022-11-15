package ru.geekbrains.spring.webstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.geekbrains.spring.webstore.dtos.Cart;
import ru.geekbrains.spring.webstore.dtos.ProductDto;
import ru.geekbrains.spring.webstore.entities.OrderItem;
import ru.geekbrains.spring.webstore.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{id}")
    public List<ProductDto> findAllItemsFromOrder(@PathVariable Long id) {
        return orderService.findAllItemsFromOrder(id)
                .stream()
                .map(orderItem -> new ProductDto(orderItem.getId(), orderItem.getProductTitle(), orderItem.getPricePerProduct()))
                .collect(Collectors.toList());
    }

}
