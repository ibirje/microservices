package com.otakuma.specific.repositories;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.jpa.repository.JpaRepository;

import com.otakuma.generic.repositories.JpaSpecs;
import com.otakuma.specific.models.Theme;


public interface ThemeRepository extends JpaRepository<Theme , Long>, JpaSpecs<Theme>  {
	
	@Caching( put = { @CachePut(value = "themesbycode", key = "#result.code", condition = "#result != null"),
			@CachePut(value = "themes", key = "#result.id", condition = "#result != null")})
		public Theme findByCode(String code);
}
