package com.sapient.publicis.casestudy.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7253162690717764551L;

	@Id
	private Integer id;
	private String productName;
	private String brand;
	private Double price;
	private String color;
	private Integer size;
	private String sku;
	private Integer quantity;

}
