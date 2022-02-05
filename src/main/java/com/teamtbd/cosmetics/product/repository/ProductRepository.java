package com.teamtbd.cosmetics.product.repository;

import com.teamtbd.cosmetics.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
