package com.otakuma.generic.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.otakuma.generic.services.BasicService;
import com.otakuma.utils.constants.Routes;

public abstract class BasicController {

	
	@SuppressWarnings("rawtypes")
	@GetMapping(Routes.PING)
	public String ping() {
		BasicService[] services = getDeclaredServices();
		if (services != null && services.length > 0)
			for(BasicService service : services)
				if(service == null || !service.check())
					return null;
		return "Pong!";
	}
	
	
	/** 
	 * implemented to verify services health. checked by using /ping
	 * */
	@SuppressWarnings("rawtypes")
	protected abstract BasicService[] getDeclaredServices();
}
