package com.otakuma.products.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakuma.products.models.Product;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ProductListResponse {

	List<Product> produits;
	Integer nbpages;
	
	
	public ProductListResponse(List<Product> produits, Integer sizewithoutpages) {
		this.produits = produits;
		this.nbpages = sizewithoutpages;
	}

	public ProductListResponse() { }
	
	public List<Product> getProducts() {
		return produits;
	}
	public void setProducts(List<Product> produits) {
		this.produits = produits;
	}
	public Integer getSizewithoutpages() {
		return nbpages;
	}
	public void setSizewithoutpages(Integer sizewithoutpages) {
		this.nbpages = sizewithoutpages;
	}
}
 