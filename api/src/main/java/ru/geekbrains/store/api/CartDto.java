package ru.geekbrains.store.api;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
public class CartDto {

    private List<CartItemDto> items;
    private BigDecimal totalPrice;

}
