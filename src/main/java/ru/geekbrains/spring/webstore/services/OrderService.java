package ru.geekbrains.spring.webstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.entities.User;
import ru.geekbrains.spring.webstore.model.CartItem;
import ru.geekbrains.spring.webstore.entities.Order;
import ru.geekbrains.spring.webstore.entities.OrderItem;
import ru.geekbrains.spring.webstore.repositories.OrderItemRepository;
import ru.geekbrains.spring.webstore.repositories.OrderRepository;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private  final ProductService productService;
    private final CartService cartService;


    public List <Order> findAll(){
        return Collections.unmodifiableList(orderRepository.findAll());
    }

    public void create(User user) {
        Order order = new Order();
        List<OrderItem> orderItems = cartService.getCurrentCart().getItems().stream()
                .map(cartItem -> new OrderItem(productService.findById(cartItem.getProductId()).orElseThrow(),
                        cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice(), order)).collect(Collectors.toList());

        order.setUser(user);
        order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
        order.setItems(orderItems);
        orderRepository.save(order);

        for (OrderItem item: orderItems) {
            orderItemRepository.save(item);
        }

    }
}
