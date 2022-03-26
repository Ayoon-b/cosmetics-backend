package com.teamtbd.cosmetics.product.dto;

import com.teamtbd.cosmetics.product.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductDto {
	private String id;
	private String name;
	private int price;
	private String brand;
	private String imageUrl;

	public static ProductDto from(Product product) {
		return new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getBrand(),
			product.getImageUrl());
	}
}
