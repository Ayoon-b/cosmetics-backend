package com.teamtbd.cosmetics.product;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@Getter
	private String id;

	@Column(nullable = false)
	private String name;

	@Column
	private int price;

	@Column
	private String brand;

	@Column
	@Convert(converter = Category.CategoryConverter.class)
	private Category category;

	@Column
	private String ingredients;

	@Column
	private String origin;

	@Column
	@Convert(converter = Site.SiteConverter.class)
	private Site site;

	@Column
	private String siteProductId;

	@Column
	private String siteCategoryId;

	@Column
	private String link;

	@Column
	private String prodUsage;

	@Column
	private String caution;

	@Column
	private String expirationDate;

	@Column
	private String imageUrl;

	@PrePersist
	public void prePersist() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}
}
