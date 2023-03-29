package com.otakuma.products.clients;

import com.otakuma.generic.clients.BasicClient;
import com.otakuma.products.models.Theme;

public class ThemeClient extends BasicClient<Theme>{

	public ThemeClient() {
		super("http://localhost:8082", Theme.class);
	}

}
