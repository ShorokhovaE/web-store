package ru.geekbrains.store.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.core.entities.User;
import ru.geekbrains.store.core.entities.Order;
import ru.geekbrains.store.core.entities.OrderItem;
import ru.geekbrains.store.core.integrations.CartServiceIntegration;
import ru.geekbrains.store.core.repositories.OrderItemRepository;
import ru.geekbrains.store.core.repositories.OrderRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public void create(User user) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart().get();

        Order order = new Order();
        order.setUser(user);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItemDto -> new OrderItem(
                        productService.findById(cartItemDto.getProductId()).get(),
                        cartItemDto.getQuantity(),
                        cartItemDto.getPricePerProduct(),
                        cartItemDto.getPrice(),
                        order
                )
        ).collect(Collectors.toList()));

        orderRepository.save(order);
        cartServiceIntegration.clear();
        //cartServiceIntegration.clear();
    }
//    private final CartService cartService;
//
//
//    public List <Order> findAll(){
//        return Collections.unmodifiableList(orderRepository.findAll());
//    }
//
//    public void create(User user) {
//        Order order = new Order();
//        List<OrderItem> orderItems = cartService.getCurrentCart().getItems().stream()
//                .map(cartItem -> new OrderItem(productService.findById(cartItem.getProductId()).orElseThrow(),
//                        cartItem.getQuantity(), cartItem.getPricePerProduct(), cartItem.getPrice(), order)).collect(Collectors.toList());
//
//        order.setUser(user);
//        order.setTotalPrice(cartService.getCurrentCart().getTotalPrice());
//        order.setItems(orderItems);
//        orderRepository.save(order);
//
//        for (OrderItem item: orderItems) {
//            orderItemRepository.save(item);
//        }
//
//    }
}
