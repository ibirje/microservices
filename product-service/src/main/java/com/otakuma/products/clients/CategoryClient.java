package com.otakuma.products.clients;

import org.springframework.http.ResponseEntity;

import com.otakuma.generic.clients.BasicClient;
import com.otakuma.products.models.Category;

import reactor.core.publisher.Mono;

public class CategoryClient extends BasicClient<Category>{
	
	public CategoryClient() {
		super("http://localhost:8081", Category.class);
	}
	
	@Override
	public Mono<ResponseEntity<Category>> getAll() {
		return super.getAll();
	}
	
}
