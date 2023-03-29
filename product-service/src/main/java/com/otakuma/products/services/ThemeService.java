package com.otakuma.products.services;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.otakuma.generic.services.BasicService;
import com.otakuma.products.models.Theme;
import com.otakuma.products.repositories.ThemeRepository;

@Component
@Scope(value="prototype")
public class ThemeService  extends BasicService<Theme, ThemeRepository>  {

	
	@Override
	protected Class<Theme> tableClass() {
		return Theme.class;
	}

	@Override
	protected void valider(Theme t) throws Exception {
		if(v.isNullOrEmpty(t.getCode()))
			throw new Exception("Theme.code invalide");
		if(v.isNomValide(t.getNom()))
			throw new Exception("Theme.nom invalide");
		if(!v.isNullOrEmpty(t.getSmallImage()) && !v.isImageValide(t.getSmallImage()))
			throw new Exception("Theme.smallImage invalide");
	}

}
