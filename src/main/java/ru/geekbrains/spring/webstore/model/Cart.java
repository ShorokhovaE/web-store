package ru.geekbrains.spring.webstore.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.spring.webstore.entities.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {

    private List<CartItem> items;
    private int totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(Product product) {
        for (CartItem item : items) {
            if(item.getProductId().equals(product.getId())){
                item.changeQuantity(1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }
    public void remove(Long productId) {

        items.removeIf(cartItem -> cartItem.getProductId().equals(productId) && cartItem.getQuantity() == 1);

        for (CartItem item : items) {
            if(item.getProductId().equals(productId)){
                item.changeQuantity(- 1);
                recalculate();
                return;
            }
        }
        recalculate();
    }

    public void clear(){
        items.clear();
        totalPrice = 0;
    }

    public void removeItem(Long productId){
        items.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
}
