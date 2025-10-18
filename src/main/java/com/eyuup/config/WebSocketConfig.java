package com.eyuup.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

        // This method configures the message broker that routes messages
        // between the server and connected WebSocket clients.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config){
        // Any destination that starts with "/topic" will be handled by this broker
        // and broadcasted to all subscribed clients.
        config.enableSimpleBroker("/topic");

    // Messages sent from the client with destinations starting with "/app"
    // will be routed to @MessageMapping methods inside your controller.
    // Example: "/app/chat" → handled by @MessageMapping("/chat").
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry){
            //  This registers the WebSocket endpoint "/ws".
            // Clients will connect to: ws://localhost:8080/ws
            registry.addEndpoint("/ws");

            // This adds SockJS support for browsers that don’t support native WebSockets.
            registry.addEndpoint("/ws").withSockJS();
            

    }
}
