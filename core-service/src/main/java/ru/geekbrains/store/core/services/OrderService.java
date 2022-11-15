package ru.geekbrains.store.core.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.core.entities.Order;
import ru.geekbrains.store.core.entities.OrderItem;
import ru.geekbrains.store.core.integrations.CartServiceIntegration;
import ru.geekbrains.store.core.repositories.OrderItemRepository;
import ru.geekbrains.store.core.repositories.OrderRepository;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductService productService;
    private final CartServiceIntegration cartServiceIntegration;

    @Transactional
    public Order createOrder(String username) {
        CartDto cartDto = cartServiceIntegration.getCurrentCart(username);
        Order order = new Order();
        order.setUsername(username);
        order.setTotalPrice(cartDto.getTotalPrice());
        order.setItems(cartDto.getItems().stream().map(
                cartItem -> new OrderItem(
                        productService.findById(cartItem.getProductId()).get(),
                        cartItem.getPrice(),
                        cartItem.getQuantity(),
                        cartItem.getPricePerProduct(),
                        order
                )
        ).collect(Collectors.toList()));
        orderRepository.save(order);
        cartServiceIntegration.clear(username);
        return order;
    }
}
