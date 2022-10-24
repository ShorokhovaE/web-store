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
        for (CartItem c : items) {
            if(c.getProductId().equals(product.getId())){
                c.setQuantity(c.getQuantity() + 1);
                recalculate();
                return;
            }
        }
        items.add(new CartItem(product.getId(), product.getTitle(), 1, product.getPrice(), product.getPrice()));
        recalculate();
    }

    private void recalculate() {
        totalPrice = 0;
        for (CartItem item : items) {
            totalPrice += item.getPrice();
        }
    }
    public void remove(Product product) {
        for (CartItem c : items) {
            if(c.getProductId().equals(product.getId())){
                if(c.getQuantity() == 1){
                    items.remove(c);
                    recalculate();
                }
                c.setQuantity(c.getQuantity() - 1);
                recalculate();
                return;
            }
        }
    }
}
