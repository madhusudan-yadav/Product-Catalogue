package com.sapient.publicis.casestudy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sapient.publicis.casestudy.domain.Product;

@Repository
public interface ProductRepository
		extends JpaRepository<Product, Integer> {

}
