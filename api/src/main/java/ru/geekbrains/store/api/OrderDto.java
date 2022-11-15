package ru.geekbrains.store.api;


import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;
    private String username;
    private BigDecimal totalPrice;
    private List<OrderItemDto> itemDtos;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public List<OrderItemDto> getItemDtos() {
        return itemDtos;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setItemDtos(List<OrderItemDto> itemDtos) {
        this.itemDtos = itemDtos;
    }

    public OrderDto(Long id, String username, BigDecimal totalPrice, List<OrderItemDto> itemDtos) {
        this.id = id;
        this.username = username;
        this.totalPrice = totalPrice;
        this.itemDtos = itemDtos;
    }
}
