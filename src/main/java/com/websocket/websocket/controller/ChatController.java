package com.websocket.websocket.controller;

import com.websocket.websocket.model.ChatMessage;
import com.websocket.websocket.model.MessageType;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import static com.websocket.websocket.constant.BaseConstant.DEFAULT_KAFKA_TOPIC;

@Controller
public class ChatController {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @MessageMapping("/chat.send")
    @SendTo("/topic/public2")
    public ChatMessage sendMessage(@Payload final ChatMessage chatMessage){
        /*
            TODO : add Message Broker
         */
        //kafkaTemplate.send(DEFAULT_KAFKA_TOPIC, chatMessage.getContent());
        System.out.println("=========== SEND MESSAGE ========");
        return chatMessage;
    }

    @MessageMapping("/chat.newUser")
    @SendTo("/topic/public2")
    public ChatMessage newUser(@Payload final ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor){
        System.out.println(chatMessage);
        headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());

        return chatMessage;
    }
}
