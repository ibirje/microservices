package com.otakuma.generic.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.otakuma.generic.services.BasicService;
import com.otakuma.utils.constants.Routes;

public class BasicController {

	
	@RequestMapping(Routes.PING)
	public String ping(BasicService service) {
		if (service != null && service.check())
			return "Pong!";
		return null;
	}
}
