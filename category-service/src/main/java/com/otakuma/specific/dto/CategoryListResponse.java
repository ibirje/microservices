package com.otakuma.specific.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.otakuma.specific.models.Category;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class CategoryListResponse {
	List<Category> categories;

	
	
	public CategoryListResponse(List<Category> categories) {
		this.categories = categories;
	}

	public List<Category> getCategorys() {
		return categories;
	}

	public void setCategorys(List<Category> categories) {
		this.categories = categories;
	}
	
	
}
