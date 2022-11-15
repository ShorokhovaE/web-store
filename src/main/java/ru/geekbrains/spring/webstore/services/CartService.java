package ru.geekbrains.spring.webstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.dtos.Cart;
import ru.geekbrains.spring.webstore.dtos.ProductDto;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;

    private final OrderService orderService;
    private Cart tempCart;

    @PostConstruct
    public void init() {
        tempCart = new Cart();
    }

    public Cart getCurrentCart() {
        return tempCart;
    }

    public void add(Long productId) {
        Product product = productService.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Не удается добавить продукт с id " + productId + " в корзину. Продукт не найден."));
        tempCart.add(product);
    }

    public void remove(Long productId) {
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        tempCart.remove(product);
    }

    public void clear(){
        tempCart.clear();
    }

    public void removeItem(Long productId){
        Product product = productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        tempCart.removeItem(product);
    }

    public void createNewOrder() {
        orderService.add(tempCart.getItems(), tempCart.getTotalPrice());
    }
}
