package ru.geekbrains.store.core.controllers;

import lombok.RequiredArgsConstructor;
import org.hibernate.dialect.Oracle8iDialect;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.store.api.OrderDto;
import ru.geekbrains.store.api.OrderItemDto;
import ru.geekbrains.store.core.services.OrderService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder(@RequestHeader String username) {
        orderService.createOrder(username);
    }

    @GetMapping
    public List<OrderDto> findAllOrders(){
        List<OrderDto> orderDtos = orderService.findAll()
                .stream()
                .map(order -> new OrderDto(order.getId(), order.getUsername(), order.getTotalPrice(),
                        order.getItems()
                                .stream()
                                .map(orderItem -> new OrderItemDto(orderItem.getId(),
                                        orderItem.getProduct().getTitle(), orderItem.getQuantity(),
                                        orderItem.getPricePerProduct(), orderItem.getPrice()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
        return orderDtos;
    }
}
