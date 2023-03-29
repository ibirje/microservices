package com.otakuma.generic.controllers;

import org.springframework.web.bind.annotation.GetMapping;

import com.otakuma.generic.services.BasicService;
import com.otakuma.utils.constants.Routes;

import io.swagger.v3.oas.annotations.Operation;

public abstract class BasicController {


	@Operation(summary = "verifies the state of all used services by this ressource")
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
