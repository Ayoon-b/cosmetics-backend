package com.teamtbd.cosmetics;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.teamtbd.cosmetics.product.Product;

import java.io.IOException;

@SpringBootTest
class CosmeticsBackendApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	void getProductInfoTest() throws IOException, ParseException {
		// "C:\\cosmeticData\\nunc_category_1_lotion.json"
		Product product = new Product();
		product.getProductInfo("C:/cosmeticData/nunc/nunc_category_1_lotion.json");
	}
}
