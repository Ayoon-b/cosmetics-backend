package com.teamtbd.cosmetics.product.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {
    private String name;
    private int price;
    private String brand;
    private String imageUrl;
}
