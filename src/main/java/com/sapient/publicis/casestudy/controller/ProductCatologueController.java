package com.sapient.publicis.casestudy.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sapient.publicis.casestudy.domain.Product;
import com.sapient.publicis.casestudy.exceptions.ProductNotFoundException;
import com.sapient.publicis.casestudy.services.ProductService;

@RestController
public class ProductCatologueController {
	@Autowired
	private ProductService productService;

	@GetMapping("/products")
	public List<Product> retrieveAllProduct() {
		return productService.findAll();
	}

	@GetMapping("/products/{id}")
	public Optional<Product> retrieveProduct(@PathVariable int id)
			throws ProductNotFoundException {

		Optional<Product> retrievedProduct = productService
				.findOne(id);
		if (!retrievedProduct.isPresent()) {
			throw new ProductNotFoundException("id --> " + id);
		}

		return retrievedProduct;
	}

	@PostMapping("/products")
	public ResponseEntity<Object> createproduct(
			@Valid @RequestBody Product product)
			throws URISyntaxException {
		Product savedProduct = productService.saveProduct(product);

		URI location = ServletUriComponentsBuilder
				.fromCurrentRequest().path("{/id}")
				.buildAndExpand(savedProduct.getId()).toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/products/{id}")
	public Optional<Product> deleteProduct(@PathVariable int id)
			throws ProductNotFoundException {

		Optional<Product> retrievedProduct = productService
				.deleteProduct(id);
		if (!retrievedProduct.isPresent()) {
			throw new ProductNotFoundException("id --> " + id);
		}
		return retrievedProduct;
	}

	@GetMapping("/products/search")
	public List<Product> retrieveAllProduct(
			@RequestParam(required = false) String brand,
			@RequestParam(required = false) Double price,
			@RequestParam(required = false) String color,
			@RequestParam(required = false) Integer size,
			@RequestParam(required = false) String sku,
			@RequestParam(required = false) Integer quantity)
			throws ProductNotFoundException {

		Product searchSimilarProduct = Product.builder().brand(brand)
				.color(color).sku(sku).build();

		searchSimilarProduct.setPrice(price);
		searchSimilarProduct.setSize(size);
		searchSimilarProduct.setQuantity(quantity);

		return productService.searchAll(searchSimilarProduct);
	}
}
