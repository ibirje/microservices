package com.otakuma.specific.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otakuma.config.cache.CacheMap;
import com.otakuma.generic.services.BasicService;
import com.otakuma.specific.models.Category;
import com.otakuma.specific.repositories.CategoryRepository;


@Service
@CacheMap({ "categories", "categoriesbycode" })
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

	public List<Category> findAll() {
		return repo.findAll();
	}
	
	public Category findByCode(String code) {
		return repo.findByCode(code);
	}
	
	
	@Override
	protected Class<Category> tableClass() { return Category.class; }
	
}
