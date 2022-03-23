package com.teamtbd.cosmetics.product.repository;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.dto.ProductDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByCategory(Category category);
    Page<ProductDto> findByCategory(Category category, Pageable pageable);

    List<Product> findByNameContains(String name);
    Page<ProductDto> findByNameContains(String name, Pageable pageable);

    List<Product> findBySite(Site site);
    List<Product> findBySite(Site site, Pageable pageable);

    @Query("select DISTINCT(p.brand) FROM Product p")
    List<String> findAllBrands();

    List<Product> findByBrand(String brand);
    Page<ProductDto> findByBrand(String brand, Pageable pageable);

    List<Product> findByPriceBetween(Integer minPrice, Integer maxPrice);

    List<Product> findByIngredientsContains(String ingredients);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> selectAll(Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByIngredientsContains(String ingredients, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByOrigin(String origin, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByVolume(String volume, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByCategoryAndNameContains(Category category, String name, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByCategoryAndBrand(Category category, String keyword, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByCategoryAndIngredientsContains(Category category, String keyword, Pageable pageable);

    @Query("SELECT new com.teamtbd.cosmetics.product.dto.ProductDto(p.name, p.price, p.brand, p.imageUrl) FROM Product p")
    Page<ProductDto> findByCategoryAndOrigin(Category category, String keyword, Pageable pageable);

}
