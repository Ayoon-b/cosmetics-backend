package com.teamtbd.cosmetics;

import com.teamtbd.cosmetics.domain.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	@Test
	void valueOf() {
		Category category = Category.valueOf(1);
		assertEquals(category, Category.SKIN_LOTION);
	}


}