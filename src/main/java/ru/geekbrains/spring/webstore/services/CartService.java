package ru.geekbrains.spring.webstore.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.spring.webstore.model.Cart;
import ru.geekbrains.spring.webstore.entities.Product;
import ru.geekbrains.spring.webstore.exceptions.ResourceNotFoundException;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {

    private final ProductService productService;
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
        productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        tempCart.remove(productId);
    }

    public void clear(){
        tempCart.clear();
    }

    public void removeItem(Long productId){
        productService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Product not found"));
        tempCart.removeItem(productId);
    }
}
