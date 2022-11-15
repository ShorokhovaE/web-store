package ru.geekbrains.store.carts.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Long productId;
    private String productTitle;
    private int quantity;
    private BigDecimal pricePerProduct;
    private BigDecimal price;

    public void changeQuantity(int delta){
        quantity += delta;
        price = new BigDecimal(String.valueOf(pricePerProduct)).multiply(new BigDecimal(String.valueOf(quantity)));

    }


}
