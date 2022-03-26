package com.teamtbd.cosmetics.product.repository;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

	List<Product> findByCategory(Category category);

	Page<Product> findByCategory(Category category, Pageable pageable);

	List<Product> findByNameContains(String name);

	Page<Product> findByNameContains(String name, Pageable pageable);

	List<Product> findBySite(Site site);

	List<Product> findBySite(Site site, Pageable pageable);

	@Query("select DISTINCT(p.brand) FROM Product p")
	List<String> findAllBrands();

	List<Product> findByBrand(String brand);

	Page<Product> findByBrand(String brand, Pageable pageable);

	List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

	List<Product> findByIngredientsContains(String ingredients);
	
	Page<Product> findByIngredientsContains(String ingredients, Pageable pageable);

	Page<Product> findByOrigin(String origin, Pageable pageable);

	Page<Product> findByVolume(String volume, Pageable pageable);

	Page<Product> findByCategoryAndNameContains(Category category, String name, Pageable pageable);

	Page<Product> findByCategoryAndBrand(Category category, String keyword, Pageable pageable);

	Page<Product> findByCategoryAndIngredientsContains(Category category, String keyword, Pageable pageable);

	Page<Product> findByCategoryAndOrigin(Category category, String keyword, Pageable pageable);

}
