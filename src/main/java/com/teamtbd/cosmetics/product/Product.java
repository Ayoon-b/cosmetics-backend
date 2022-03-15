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
@ToString
@Getter
@Setter
public class Product {

	@Id
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
	@Lob
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

	@Column(length = 512)
	private String link;

	@Column
	private String volume;

	@Column
	@Lob
	private String prodUsage;

	@Column
	@Lob
	private String caution;

	@Column
	private String expirationDate;

	@Column(length = 512)
	private String imageUrl;

	@Column
	private Integer reviewCount;

	@Column
	private Integer totalRating;

	@PrePersist
	public void prePersist() {
		if (id == null) {
			id = UUID.randomUUID().toString();
		}
	}
}
