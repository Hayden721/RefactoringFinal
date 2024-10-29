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
    public MessageDto chatEnter(@DestinationVariable Long roomNo, SimpMessageHeaderAccessor headerAccessor
                                , @Payload MessageDto messageDto) {

        logger.info("seder: {}", messageDto);
        String username = messageDto.getSender();
        // 유저 닉네임 가져오기
//        String username = (String) headerAccessor.getSessionAttributes().get("user");

        boolean userPresence = stompChatService.getUserPresenceByUserName(username, roomNo);
        logger.info("userPresence: {}", userPresence);

        if(!userPresence) {
            stompChatService.saveEnterUser(username, roomNo);

            MessageDto enterMessage = new MessageDto();
            enterMessage.setSender(username);
            enterMessage.setChatroomNo(roomNo);
            enterMessage.setMessageContent(username + "님이 입장했습니다.");
            enterMessage.setType(MessageDto.MessageType.ENTER);
            return enterMessage;
        }

        return null;

    }

    @MessageMapping("/exit/{roomNo}")
    @SendTo("/topic/messages/{roomNo}")
    public MessageDto exitChatroom(@DestinationVariable Long roomNo, @Payload MessageDto messageDto) {
//        String username = (String) headerAccessor.getSessionAttributes().get("user");
        String username = messageDto.getSender();

        MessageDto exitMessage = new MessageDto();

        exitMessage.setSender(username);
        exitMessage.setChatroomNo(roomNo);
        exitMessage.setMessageContent(username + "님이 퇴장했습니다.");
        exitMessage.setType(MessageDto.MessageType.EXIT);
        logger.info("exitMessage : {}", exitMessage);

        logger.info("messageMapping : /exit/room");

        // 채팅방 유저 삭제
        stompChatService.leaveChatroomByUsername(username, roomNo);

        return exitMessage;

    }



}
