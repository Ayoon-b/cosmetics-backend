package com.teamtbd.cosmetics.product.service;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.domain.Site;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product getProductFromJsonObject(JSONObject jsonObject) {
        return Product.builder()
                .id(String.valueOf(jsonObject.get("id")))
                .name(String.valueOf(jsonObject.get("name")))
                .price(Integer.parseInt(String.valueOf(jsonObject.get("price")).replace(",", "")))
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

    public List<Product> getProductsFromJsonArray(JSONArray jsonArray){
        List<Product> productList = new ArrayList<>();

        for(int i = 0; i<jsonArray.size(); i++){
            productList.add(getProductFromJsonObject((JSONObject) jsonArray.get(i)));
        }

        return productList;
    }

    @Transactional
    public void saveProductsFromPaths(List<Path> paths) throws IOException, ParseException {

        for(int idx = 0; idx < paths.size(); idx++){
            JSONParser parser = new JSONParser();
            Reader reader = new FileReader(paths.get(idx).toString());
            Object obj = parser.parse(reader);

            if (obj instanceof JSONObject){
                JSONObject jsonObject = (JSONObject) obj;
                Product product = getProductFromJsonObject(jsonObject);
                productRepository.save(product);

            } else if (obj instanceof JSONArray){
                JSONArray jsonArray = (JSONArray) obj;
                List<Product> products = getProductsFromJsonArray(jsonArray);
                productRepository.saveAll(products);

            } else {
                throw new IllegalArgumentException("JSON 형식이 아닙니다.");
            }
        }

    }

    public List<Path> getFileList(String filePath) throws IOException {
        Path path = Paths.get(filePath);
        Stream<Path> paths = Files.walk(path);
        List<Path> pathList = paths.filter(Files::isRegularFile).collect(Collectors.toList());

        return pathList;
    }
    
    public List<Product> getProductsByCategory(Category category){
        return productRepository.findByCategory(category);
    }

    public List<Product> getProductsByNameContains(String name){
        return productRepository.findByNameContains(name);
    }

    public List<Product> getProductsBySite(Site site){
        return productRepository.findBySite(site);
    }

    public List<Product> getProductsByCategory(Category category, Pageable pageable){
        return productRepository.findByCategory(category, pageable);
    }

    public List<Product> getProductsByNameContains(String name, Pageable pageable){
        return productRepository.findByNameContains(name, pageable);
    }

    public List<Product> getProductsBySite(Site site, Pageable pageable){
        return productRepository.findBySite(site,pageable);
    }

    public List<String> getAllBrands(){
        return productRepository.findAllBrands();
    }

    public Page<Product> getProducts(PageRequest pageable) {
        return productRepository.findAll(pageable);
    }

    public List<Product> getProductsByBrand(String brand){
        return productRepository.findByBrand(brand);
    }

    public List<Product> getProductsByPriceRange(Integer minPrice, Integer maxPrice){
        return productRepository.findByPriceBetween(minPrice, maxPrice);
    }

    public List<Product> getProductsByIngredientsContains(String ingredients) {
        return productRepository.findByIngredientsContains(ingredients);
    }

    public Page<Product> getProductsByPriceAsc() {
        return productRepository.findAll(PageRequest.of(0, 24, Sort.by("price")));
    }

    public Page<Product> getProductsByPriceDesc() {
        return productRepository.findAll(PageRequest.of(0, 24, Sort.by("price").descending()));
    }

    public Page<Product> getProductsByCategoryAndName(Category category, String name, Pageable pageable) {
        return productRepository.findByCategoryAndNameContains(category, name, pageable);
    }

}
