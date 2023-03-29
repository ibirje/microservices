package com.otakuma.generic.clients;

import org.springframework.http.ResponseEntity;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

public abstract class BasicClient<T> {
	
	
	protected Class<T> type;
	protected WebClient client;
	protected String service;
	
	
	
	
	public BasicClient(String service, Class<T> type) {
		
		this.service = service;
		
	    TcpClient tcpClient = TcpClient.newConnection();
	    
	    @SuppressWarnings("deprecation")
		HttpClient httpClient = HttpClient.from(tcpClient) .tcpConfiguration(
			client -> client.option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 60 * 1000).doOnConnected(conn -> conn.addHandlerLast(new ReadTimeoutHandler(60))));

	    client = WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
	}

	
	 protected Mono<ResponseEntity<T>> GETRequest(String service,String resource) { 
		WebClient webClient = WebClient.create(service);
		return webClient.get()
		        .uri(resource)
		        .retrieve().toEntity(type);
	}
	 
	 
	public Mono<ResponseEntity<T>> GETRequest(String resource) { 
		WebClient webClient = WebClient.create(service);
		return webClient.get()
		        .uri(resource)
		        .retrieve().toEntity(type);
	}
	/** should not be exposed or implemented in the service when dealing with sensitive data */
	protected Mono<ResponseEntity<T>> getAll() {
		return GETRequest("/");
	}
}
