package com.otakuma.specific.repositories;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.specific.models.Category;


public interface CategoryRepository extends JpaRepository<Category , Long>, JpaSpecs<Category>  {
	
	@Caching( put = { @CachePut(value = "categoriesbycode", key = "#result.code", condition = "#result != null"),
			@CachePut(value = "categories", key = "#result.id", condition = "#result != null")})
		public Category findByCode(String code);
}
