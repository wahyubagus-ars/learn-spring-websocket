package com.websocket.websocket.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@AllArgsConstructor
@Getter
@Setter
public class ChatMessage {
    private MessageType type;
    private String content;
    private String sender;
    private String time;
}
