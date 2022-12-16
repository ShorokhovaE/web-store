package ru.geekbrains.store.core.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.geekbrains.store.api.CartDto;
import ru.geekbrains.store.api.CartItemDto;
import ru.geekbrains.store.api.OrderItemDto;
import ru.geekbrains.store.core.entities.Order;
import ru.geekbrains.store.core.entities.Product;
import ru.geekbrains.store.core.repositories.OrderRepository;
import ru.geekbrains.store.core.integrations.CartServiceIntegration;
import ru.geekbrains.store.core.services.OrderService;
import ru.geekbrains.store.core.services.ProductService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class OrderServiceTest {

    @Autowired
    private OrderService orderService;
    @MockBean
    private CartServiceIntegration cartServiceIntegration;
    @MockBean
    private ProductService productService;
    @MockBean
    private OrderRepository orderRepository;

    @Test
    public void createOrderTest() {
//        CartDto cartDto = new CartDto();
////        CartItemDto cartItemDto = new CartItemDto();
////        cartItemDto.setProductTitle("Milk");
////        cartItemDto.setPricePerProduct(BigDecimal.valueOf(100));
////        cartItemDto.setQuantity(2);
////        cartItemDto.setPrice(BigDecimal.valueOf(200));
////        cartItemDto.setProductId(19267L);
//        cartDto.setTotalPrice(BigDecimal.valueOf(200));
//        cartDto.setItems(List.of(cartItemDto));

        CartItemDto cartItemDto = CartItemDto.builder()
                .productTitle("Milk")
                .pricePerProduct(BigDecimal.valueOf(100))
                .quantity(2)
                .price(BigDecimal.valueOf(200))
                .productId(19267L)
                .build();

        CartDto cartDto = CartDto.builder()
                .totalPrice(BigDecimal.valueOf(200))
                .items(List.of(cartItemDto))
                .build();

        Mockito.doReturn(cartDto).when(cartServiceIntegration).getCurrentCart("1");

        Product product = new Product();
        product.setId(19267L);
        product.setPrice(BigDecimal.valueOf(100));
        product.setTitle("Milk");

        Mockito.doReturn(Optional.of(product)).when(productService).findById(19267L);

        Order order = orderService.createOrder("Bob");
        Assertions.assertEquals(order.getTotalPrice(), BigDecimal.valueOf(200));

        Mockito.verify(orderRepository, Mockito.times(1)).save(ArgumentMatchers.any());
    }


}
