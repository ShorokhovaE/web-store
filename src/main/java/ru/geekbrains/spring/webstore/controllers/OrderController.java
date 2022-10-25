package ru.geekbrains.spring.webstore.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.spring.webstore.dtos.OrderDto;
import ru.geekbrains.spring.webstore.dtos.OrderItemDto;
import ru.geekbrains.spring.webstore.entities.User;
import ru.geekbrains.spring.webstore.exceptions.ResourceNotFoundException;
import ru.geekbrains.spring.webstore.services.OrderService;
import ru.geekbrains.spring.webstore.services.UserService;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createOrder (Principal principal) {

        User user = userService.findByUsername(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("Юзер не найден"));

        orderService.create(user);
    }

    @GetMapping
    public List<OrderDto> findAllOrder() {

        return orderService.findAll()
                .stream()
                .map(order -> new OrderDto(
                        order.getId(),
                        order.getUser().getUsername(),
                        order.getTotalPrice(),
                        order.getItems()
                                .stream()
                                .map(orderItem -> new OrderItemDto(
                                        orderItem.getId(),
                                        orderItem.getProduct().getTitle(),
                                        orderItem.getQuantity(),
                                        orderItem.getPricePerProduct(),
                                        orderItem.getPrice()))
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }

}
