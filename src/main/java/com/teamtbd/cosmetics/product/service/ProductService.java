package com.teamtbd.cosmetics.product.service;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductFromJsonObject(JSONObject jsonObject) {
        return Product.builder()
                .id(String.valueOf(jsonObject.get("id")))
                .name(String.valueOf(jsonObject.get("name")))
                .price(Integer.parseInt(String.valueOf(jsonObject.get("price"))))
                .brand(String.valueOf(jsonObject.get("brand")))
                .imageUrl(String.valueOf(jsonObject.get("image_url")))
                .category(Category.valueOf(Integer.parseInt(String.valueOf(jsonObject.get("category")))))
                .site(Site.valueOf(Integer.parseInt(String.valueOf(jsonObject.get("site")))))
                .siteProductId(String.valueOf(jsonObject.get("site_id")))
                .link(String.valueOf(jsonObject.get("link")))
                .siteCategoryId(String.valueOf(jsonObject.get("site_category")))
                .volume(String.valueOf(jsonObject.get("volume")))
                .expirationDate(String.valueOf(jsonObject.get("expiration_date")))
                .prodUsage(String.valueOf(jsonObject.get("usage")))
                .origin(String.valueOf(jsonObject.get("origin")))
                .ingredients(String.valueOf(jsonObject.get("ingredients")))
                .caution(String.valueOf(jsonObject.get("caution")))
                .build();
    }
}
