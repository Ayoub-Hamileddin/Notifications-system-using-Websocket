package com.eyuup.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class NotificationSystem {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    public String Notification(String message){
        return message;
    }

}
