package com.refactoring.finalproject.chat.controller;

import com.refactoring.finalproject.chat.dao.StompChatDao;
import com.refactoring.finalproject.chat.dto.ChatMessageDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;

@Controller
public class StompChatController {

    private final Logger logger = LogManager.getLogger(this.getClass());
    private final SimpMessagingTemplate messagingTemplate;
    private final StompChatDao stompChatDao;

    public StompChatController(SimpMessagingTemplate messagingTemplate, StompChatDao stompChatDao) {
        this.messagingTemplate = messagingTemplate;
        this.stompChatDao = stompChatDao;
    }


    // 메시지 송수신
    @MessageMapping("/send/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public ChatMessageDto sendMessage(@DestinationVariable Long roomNo, @Payload ChatMessageDto message) {
        ChatMessageDto responseMessage = new ChatMessageDto();
        responseMessage.setSender(message.getSender());
        responseMessage.setChatMessage(message.getChatMessage());
        responseMessage.setChatroomNo(roomNo);

        logger.info("responseMessage: {}", responseMessage);
        // 채팅을 저장하고 저장한것을 보내기 : stompChatService.save
        return responseMessage;
    }

    // 입장 멘트
    @MessageMapping("/join/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public ChatMessageDto chatJoin(@DestinationVariable Long roomNo, SimpMessageHeaderAccessor headerAccessor) {

        String username = (String) headerAccessor.getSessionAttributes().get("user");
        logger.info("Session username: {}", username);


        ChatMessageDto joinMessage = new ChatMessageDto();
        joinMessage.setSender("System");
        joinMessage.setChatroomNo(roomNo);
        joinMessage.setChatMessage(username + "님이 입장했습니다.");

        return joinMessage;
    }

}
