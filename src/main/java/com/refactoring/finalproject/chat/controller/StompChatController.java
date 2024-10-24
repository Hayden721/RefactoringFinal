package com.refactoring.finalproject.chat.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.refactoring.finalproject.chat.dao.StompChatDao;
import com.refactoring.finalproject.chat.dto.ChatMessageDto;
import com.refactoring.finalproject.chat.service.StompChatService;
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
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

@Controller
public class StompChatController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final StompChatService stompChatService;

    public StompChatController(StompChatService stompChatService) {
        this.stompChatService = stompChatService;
    }


    // 메시지 송수신
    @ResponseBody
    @MessageMapping("/send/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public ChatMessageDto sendMessage(@DestinationVariable Long roomNo, @Payload ChatMessageDto message) {
        ObjectMapper objectMapper = new ObjectMapper();
        logger.info("message : {}", message);
        // 메시지 저장

        // LocalDateTime Json 변환

         ChatMessageDto saveMessage = stompChatService.saveAndSendMessage(message);


        return saveMessage;
    }

    // 입장 멘트
    @MessageMapping("/enter/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public ChatMessageDto chatJoin(@DestinationVariable Long roomNo, SimpMessageHeaderAccessor headerAccessor) {
        // 유저 닉네임 가져오기
        String username = (String) headerAccessor.getSessionAttributes().get("user");

//        boolean userPresence = stompChatService.getUserPresenceByUserName(username);
//        logger.info("userPresence: {}", userPresence);


        logger.info("Session username: {}", username);

//        stompChatService.saveEnterUser(username, roomNo);

        ChatMessageDto joinMessage = new ChatMessageDto();
        joinMessage.setSender("System");
        joinMessage.setChatroomNo(roomNo);
        joinMessage.setChatMessage(username + "님이 입장했습니다.");

        return joinMessage;
    }



}
