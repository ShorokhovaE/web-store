package ru.geekbrains.spring.webstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.dtos.Cart;
import ru.geekbrains.spring.webstore.dtos.CartItem;
import ru.geekbrains.spring.webstore.entities.Order;
import ru.geekbrains.spring.webstore.entities.OrderItem;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.repositories.OrderRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List <OrderItem> findAllItemsFromOrder(Long orderId){
        return Collections.unmodifiableList(orderRepository.findById(orderId).orElseThrow().getItems());
    }

    public void add(List<CartItem> item, int totalPrice) {
        Order newOrder = new Order();
        List<OrderItem> newOrderItems = item.stream()
                .map(cartItem -> new OrderItem(cartItem.getProductId(), cartItem.getProductTitle(),
                        cartItem.getQuantity(), cartItem.getPricePerProduct(),
                        cartItem.getPrice(), newOrder)).collect(Collectors.toList());

        newOrder.setItems(newOrderItems);
        newOrder.setTotalPrice(totalPrice);

        orderRepository.save(newOrder);

    }
}
