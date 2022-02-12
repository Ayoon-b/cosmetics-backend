package com.teamtbd.cosmetics.product.controller;

import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/products")
    public Page<Product> getProducts(@RequestParam(defaultValue = "0") Integer page){
        return productService.getProducts(PageRequest.of(page, 24));
    }
}
