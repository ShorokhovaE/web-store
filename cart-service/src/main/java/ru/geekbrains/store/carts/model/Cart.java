package ru.geekbrains.store.carts.model;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.geekbrains.store.api.ProductDto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@Component
public class Cart {

    private List<CartItem> items;
    private BigDecimal totalPrice;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void add(ProductDto product) {
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
        totalPrice = BigDecimal.ZERO;
    }

    public void removeItem(Long productId){
        items.removeIf(cartItem -> cartItem.getProductId().equals(productId));
        recalculate();
    }

    private void recalculate() {
        totalPrice =  BigDecimal.ZERO;
        for (CartItem item : items) {
            totalPrice.add(item.getPrice());
        }
    }
}
