package com.main.WebSocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer{

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
// 		WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
 		
 		registry.enableSimpleBroker("/topic");
 		//  /topic/cricket
 		//  /topic/order
 		
 		registry.setApplicationDestinationPrefixes("/app"); // prefix of end point url here
 		
 		
 		
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
// 		WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		registry.addEndpoint("/ticketwebsocket")
		.setAllowedOriginPatterns("*")  // host and port of client
		.withSockJS();
	}

	
}
