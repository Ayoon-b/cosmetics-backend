package com.teamtbd.cosmetics.product.repository;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ProductRepositoryTest {

	@Autowired
	ProductRepository productRepository;

	@Test
	@Rollback(value = false)
	void saveTest() throws Throwable{

		Product product = Product.builder()
			.id("aaaaa")
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
			.volume("1000")
			.build();

		product = productRepository.save(product);

		assertNotNull(product.getId());

	}

}
