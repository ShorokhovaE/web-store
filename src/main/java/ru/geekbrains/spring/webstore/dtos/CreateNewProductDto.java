package ru.geekbrains.spring.webstore.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateNewProductDto {
    private String title;
    private int price;
}
