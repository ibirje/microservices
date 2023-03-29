package com.otakuma.specific.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.otakuma.config.cache.CacheMap;
import com.otakuma.generic.services.BasicService;
import com.otakuma.specific.models.Theme;
import com.otakuma.specific.repositories.ThemeRepository;


@Service
@CacheMap({ "themes", "themesbycode" })
public class ThemeService extends BasicService<Theme, ThemeRepository> {


	@Override
	protected void valider(Theme t) throws Exception {
		if(v.isNullOrEmpty(t.getCode()))
			throw new Exception("Theme.code invalide");
		if(v.isNomValide(t.getNom()))
			throw new Exception("Theme.nom invalide");
		if(!v.isNullOrEmpty(t.getSmallImage()) && !v.isImageValide(t.getSmallImage()))
			throw new Exception("Theme.smallImage invalide");
	}

	public List<Theme> findAll() {
		return repo.findAll();
	}
	
	public Theme findByCode(String code) {
		return repo.findByCode(code);
	}
	
	
	@Override
	protected Class<Theme> tableClass() { return Theme.class; }
	
}
