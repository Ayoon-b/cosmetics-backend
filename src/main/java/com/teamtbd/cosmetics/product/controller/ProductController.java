package com.teamtbd.cosmetics.product.controller;

import com.teamtbd.cosmetics.domain.Category;
import com.teamtbd.cosmetics.product.Product;
import com.teamtbd.cosmetics.product.dto.ProductDto;
import com.teamtbd.cosmetics.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class ProductController {

	private final ProductService productService;

	@GetMapping(path = "/products")
	public Page<ProductDto> getProducts(@RequestParam(defaultValue = "0") Integer page,
										@RequestParam(value = "category_id", required = false) Integer categoryId,
										@RequestParam(value = "search", required = false) String search,
										@RequestParam(value = "sortBy", defaultValue = "price") String sortBy,
										@RequestParam(value = "orderBy", defaultValue = "asc") String orderBy,
										@RequestParam(value = "filterBy", defaultValue = "NAME") String filterBy) {

		Sort sort = Sort.by(orderBy.equalsIgnoreCase("desc")
							? Sort.Order.desc(sortBy)
							: Sort.Order.asc(sortBy));
		PageRequest pageable = PageRequest.of(page, 24, sort);

		Filter filter = Filter.valueOf(filterBy.toUpperCase());

		if (categoryId != null) {
			Category category = Category.valueOf(categoryId);
			if (search != null) {
				return productService.getProductsByFilterAndCategory(filter, search, category, pageable)
					.map(ProductDto::from);
			}
			return productService.getProductsByCategory(category, pageable).map(ProductDto::from);
		}
		if (search != null) {
			return productService.getProductsByFilter(filter, search, pageable).map(ProductDto::from);
		}
		return productService.getProducts(pageable).map(ProductDto::from);
	}

	@GetMapping("/products/{id}")
	public Optional<Product> getProduct(@PathVariable("id") String id) {
		return productService.getProduct(id);
	}

	@GetMapping("/products/{ids}")
	public List<ProductDto> getRecentProducts(@RequestParam("ids") List<String> ids){
		return productService.getProductsByIds(ids).stream().map(ProductDto::from).collect(Collectors.toList());
	}
}
