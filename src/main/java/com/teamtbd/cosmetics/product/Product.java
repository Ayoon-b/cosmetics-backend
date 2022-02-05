package com.teamtbd.cosmetics.product;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


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

	public void getProductInfo(String dirUrl) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		// JSON 파일 읽기
		Reader reader = new FileReader(dirUrl);
		JSONObject jsonObject = (JSONObject) parser.parse(reader);
		String name = (String) jsonObject.get("name");
		long price = (Long) jsonObject.get("price");
		String brand = (String) jsonObject.get("brand");
		System.out.println(name);
		System.out.println(price);
		System.out.println(brand);

	}


}
