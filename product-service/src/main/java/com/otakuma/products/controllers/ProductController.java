package com.otakuma.products.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.otakuma.generic.controllers.BasicController;
import com.otakuma.products.clients.CategoryClient;
import com.otakuma.products.dto.ProductListResponse;
import com.otakuma.products.services.ProductService;
import com.otakuma.utils.constants.Routes;

@RestController
public class ProductController extends BasicController{

	
	@Autowired
	private ProductService service;

	@Autowired
	private CategoryClient categoryClient;
	
	
	@RequestMapping({ Routes.DEF, Routes.LIST, Routes.SLASH })
	public ProductListResponse list(
			@RequestParam(required = false, value = "code") String code,
			@RequestParam(required = false, value = "nom") String titre,
			@RequestParam(required = false, value = "ispromo") String ispromo, /* */
			
			@RequestParam(required = false, value = "prixmin") Double prixmin,
			@RequestParam(required = false, value = "prixmax") Double prixmax,
			
			@RequestParam(required = false, value = "prixpromomin") Double prixpromomin,
			@RequestParam(required = false, value = "prixpromomax") Double prixpromomax,
			
			@RequestParam(required = false, value = "qtemin") Integer qtemin,
			@RequestParam(required = false, value = "qtemax") Integer qtemax,
			
			@RequestParam(required = false, value = "minreduc") Integer minreduc,
			@RequestParam(required = false, value = "maxreduc") Integer maxreduc,
			
			@RequestParam(required = false, value = "size") Integer size,
			@RequestParam(required = false, value = "page") Integer page) {
		
		return service.listRecherche(code, titre, ispromo, 
				prixmin, prixmax, prixpromomin, prixpromomax,
				qtemin, qtemax, minreduc, maxreduc, size, page);
	}

	@RequestMapping({ Routes.CATEGORIES })
	public Object getAuthors() {
		return categoryClient.getAll();
	}
}
