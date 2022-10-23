package ru.geekbrains.spring.webstore.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
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
        CartItem cartItem = new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        if (!items.contains(cartItem)) {
            items.add(cartItem);
        } else {
            int i = items.indexOf(cartItem);
            int quantity = items.get(i).getQuantity() + 1;
            items.get(i).setQuantity(quantity);
            items.get(i).setPrice(quantity * items.get(i).getPrice());
        }
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }

    public void remove(Product product) {
        CartItem cartItem =
                new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice());
        if (!items.contains(cartItem)) {
            return;
        } else {
            int i = items.indexOf(cartItem);
            int quantity = items.get(i).getQuantity();
            if(quantity > 1){
                quantity = items.get(i).getQuantity() - 1;
                items.get(i).setQuantity(quantity);
                items.get(i).setPrice(quantity * items.get(i).getPrice());
            } else {
                items.remove(cartItem);
            }
        }
        recalculate();
    }
}
