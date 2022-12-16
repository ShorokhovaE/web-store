package ru.geekbrains.store.api;


import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;


@Data
@Builder
public class OrderDto {

    private Long id;
    private String username;
    private BigDecimal totalPrice;
    private List<OrderItemDto> itemDtos;

}
