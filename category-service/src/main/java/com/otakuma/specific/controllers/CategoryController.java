package com.otakuma.specific.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otakuma.generic.controllers.BasicController;
import com.otakuma.generic.services.BasicService;
import com.otakuma.specific.dto.CategoryListResponse;
import com.otakuma.specific.models.Category;
import com.otakuma.specific.services.CategoryService;
import com.otakuma.utils.constants.Routes;

import io.swagger.v3.oas.annotations.Operation;

@RestController
public class CategoryController extends BasicController{
	
	
	@Autowired
	private CategoryService service;
	
	@Operation(summary = "find categories list")
	/** find categories list */
	@GetMapping({ Routes.DEF, Routes.LIST, Routes.SLASH })
	public CategoryListResponse list() {
		return new CategoryListResponse(service.findAll());
	}


	@Operation(summary = "find a category by code")
	/** find a category by code */
	@GetMapping(Routes.CODE)
	public Category findByCode(@PathVariable String code) {
		return service.findByCode(code);
	}

	@Operation(summary = "add a new category")
	/** add a new category */
	@PostMapping({ Routes.DEF, Routes.SLASH })
	public Category create(@PathVariable String code) {
		return service.findByCode(code);
	}


	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BasicService[] getDeclaredServices() {
		return new BasicService[]{service};
	}
	
	
}
