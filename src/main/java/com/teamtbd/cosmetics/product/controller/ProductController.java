package com.teamtbd.cosmetics.product.controller;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/products")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") Integer page,
                                     @RequestParam(value = "category_id", required = false) Integer categoryId,
                                     @RequestParam(value = "search", required = false) String search,
                                     @RequestParam(value = "sortBy", defaultValue = "price") String sortBy,
                                     @RequestParam(value = "orderBy", defaultValue = "asc") String orderBy){
        Sort sort = Sort.by(orderBy.equalsIgnoreCase("desc")
                ? Sort.Order.desc(sortBy)
                : Sort.Order.asc(sortBy));
        PageRequest pageable = PageRequest.of(page, 24, sort);

        if (categoryId != null) {
            Category category = Category.valueOf(categoryId);
            if (search != null) {
                return productService.getProductsByCategoryAndName(category, search, pageable);
            }
            return productService.getProductsByCategory(category, pageable);
        }
        if (search != null) {
            return productService.getProductsByNameContains(search, pageable);
        }
        return productService.getProducts(pageable);
    }

    @GetMapping(path = "/product")
    public Optional<Product> getProduct(@RequestParam(defaultValue = "0") String id){
        return productService.getProduct(id);
    }
}
