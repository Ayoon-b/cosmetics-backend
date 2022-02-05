package com.teamtbd.cosmetics.product.repository;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

	@Autowired
    ProductRepository productRepository;

	@Test
	void saveTest() {
		Product product = Product.builder()
			.name("test")
			.caution("test")
			.brand("test")
			.category(Category.CREAM)
			.expirationDate("test")
			.imageUrl("test")
			.ingredients("test")
			.link("test")
			.origin("test")
			.price(2134)
			.siteProductId("test")
			.siteCategoryId("test")
			.site(Site.NUNC)
			.prodUsage("test")
			.caution("test")
			.build();

		assertNull(product.getId());
		product = productRepository.save(product);

		assertNotNull(product.getId());
	}
}
