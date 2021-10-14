package com.websocket.websocket.web;


import com.websocket.websocket.model.ChatMessage;
import com.websocket.websocket.model.MessageType;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.logging.Logger;

@Component
public class WebSocketEventListener {

    @Autowired
    private SimpMessageSendingOperations simpMessageSendingOperations;

    @EventListener
    public void handleWebsocketEventListener(final SessionConnectedEvent event){ }

    @EventListener
    public void handleWebSocketDisconnectListener(final SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

        final String username = (String) headerAccessor.getSessionAttributes().get("username");

        final ChatMessage chatMessage = ChatMessage.builder().
                type(MessageType.DISCONNECT).
                sender(username).
                build();

        simpMessageSendingOperations.convertAndSend("/topic/public2", chatMessage);
    }
}
