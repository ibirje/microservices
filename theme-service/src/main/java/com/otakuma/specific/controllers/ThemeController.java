package com.otakuma.specific.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.otakuma.generic.controllers.BasicController;
import com.otakuma.generic.services.BasicService;
import com.otakuma.specific.dto.ThemeListResponse;
import com.otakuma.specific.models.Theme;
import com.otakuma.specific.services.ThemeService;
import com.otakuma.utils.constants.Routes;

@RestController
public class ThemeController extends BasicController{
	
	
	@Autowired
	private ThemeService service;
	
	
	/** find themes list */
	@GetMapping({ Routes.DEF, Routes.LIST, Routes.SLASH })
	public ThemeListResponse list() {
		return new ThemeListResponse(service.findAll());
	}


	/** find a theme by code */
	@GetMapping(Routes.CODE)
	public Theme findByCode(@PathVariable String code) {
		return service.findByCode(code);
	}

	/** add a new theme */
	@PostMapping({ Routes.DEF, Routes.SLASH })
	public Theme create(@PathVariable String code) {
		return service.findByCode(code);
	}


	
	
	
	
	
	
	
	@SuppressWarnings("rawtypes")
	@Override
	protected BasicService[] getDeclaredServices() {
		return new BasicService[]{service};
	}
	
	
}
