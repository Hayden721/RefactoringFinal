package com.refactoring.finalproject.chat.controller;

import com.refactoring.finalproject.chat.dto.MessageDto;
import com.refactoring.finalproject.chat.service.StompChatService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;

import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

@Controller
public class StompChatController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final StompChatService stompChatService;

    public StompChatController(StompChatService stompChatService) {
        this.stompChatService = stompChatService;
    }


    // 메시지 송수신
    @MessageMapping("/send/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public MessageDto sendMessage(@DestinationVariable Long roomNo, @Payload MessageDto messageDto) {

        logger.info("message : {}", messageDto);

        return stompChatService.saveAndSendMessage(messageDto);
    }

    // 입장 멘트
    @MessageMapping("/enter/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public MessageDto chatEnter(@DestinationVariable Long roomNo, SimpMessageHeaderAccessor headerAccessor) {
        // 유저 닉네임 가져오기
        String username = (String) headerAccessor.getSessionAttributes().get("user");

        boolean userPresence = stompChatService.getUserPresenceByUserName(username, roomNo);
        logger.info("userPresence: {}", userPresence);

        if(!userPresence) {
            stompChatService.saveEnterUser(username, roomNo);
            MessageDto enterMessage = new MessageDto();

            enterMessage.setChatroomNo(roomNo);
            enterMessage.setMessageContent(username + "님이 입장했습니다.");

            return enterMessage;
        }

        return null;

    }



}
