package com.main.WebSocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller

public class NotificationController {

	
	// Server application
	
	//  /app/sendMessage
	@MessageMapping("/sendMessage")
	@SendTo("/topic/notification")      //  /topic/cricket  //  /topic/order  to where to send Notification of api
	public String sendMessage(String message) {
		System.out.println("Message : " + message);
		return message;
	}
}
