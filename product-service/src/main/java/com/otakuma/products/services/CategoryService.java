package com.otakuma.products.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otakuma.generic.services.BasicService;
import com.otakuma.products.models.Category;
import com.otakuma.products.repositories.CategoryRepository;


@Service
public class CategoryService extends BasicService<Category, CategoryRepository> {


	@Override
	protected void valider(Category t) throws Exception {
		if(v.isNullOrEmpty(t.getCode()))
			throw new Exception("Category.code invalide");
		if(v.isNomValide(t.getNom()))
			throw new Exception("Category.nom invalide");
		if(!v.isNullOrEmpty(t.getSmallImage()) && !v.isImageValide(t.getSmallImage()))
			throw new Exception("Category.smallImage invalide");
	}

	@Override
	protected Class<Category> tableClass() { return Category.class; }

	public List<Category> getListe() {
		return repo.findAll();
	}
}
