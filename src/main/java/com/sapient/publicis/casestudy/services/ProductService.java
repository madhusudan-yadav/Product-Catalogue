package com.sapient.publicis.casestudy.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.sapient.publicis.casestudy.domain.Product;
import com.sapient.publicis.casestudy.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;

	public List<Product> findAll() {
		return productRepository.findAll();
	}

	public Optional<Product> findOne(int id) {
		return productRepository.findById(id);
	}

	public Product saveProduct(@Valid Product product) {
		return productRepository.save(product);
	}

	public Optional<Product> deleteProduct(int id) {
		Optional<Product> searchedProduct = findOne(id);
		if (searchedProduct.isPresent()) {
			productRepository.deleteById(id);
		}
		return searchedProduct;
	}

	public List<Product> searchAll(Product product) {
		Example<Product> example = Example.of(product);
		return productRepository.findAll(example);
	}
}
