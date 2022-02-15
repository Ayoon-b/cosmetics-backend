package com.teamtbd.cosmetics.repository;

import com.teamtbd.cosmetics.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
