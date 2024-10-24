package com.refactoring.finalproject.chat.service.impl;

import com.refactoring.finalproject.chat.dao.StompChatDao;
import com.refactoring.finalproject.chat.dto.ChatMessageDto;
import com.refactoring.finalproject.chat.service.StompChatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Service
public class StompChatImpl implements StompChatService {

    private static final Logger logger = LoggerFactory.getLogger(StompChatImpl.class);
    private final StompChatDao stompChatDao;

    public StompChatImpl(StompChatDao stompChatDao) {
        this.stompChatDao = stompChatDao;
    }

    /**
     * 채팅방에 존재하는 유저들을 반환합니다.
     * @param username :
     * @return
     */
    @Override
    public boolean getUserPresenceByUserName(String username) {

//        Long userNo = stompChatDao.selectUserNoByUsername(username);

        return false;

//                stompChatDao.selectEnteredChatroomUserBy();
    }

    @Override
    public void saveEnterUser(String username, Long roomNo) {
        boolean confirmUser =  stompChatDao.selectConfirmEnterUser(username, roomNo);
//        try {
//            if (confirmUser) {
//                stompChatDao.insertEnterUser(username, roomNo);
//            }
//        }catch (Exception e){
//
//        }

    }

    @Override
    public ChatMessageDto saveAndSendMessage(ChatMessageDto responseMessage) {
        logger.info("responseMessage: {}", responseMessage);
        LocalDateTime now = LocalDateTime.now();

        try {

            // 날짜 추가
            responseMessage.setMessageDate(now);
            responseMessage.setSenderNo(stompChatDao.selectUserNoByUsername(responseMessage.getSender()));

            int result = stompChatDao.insertMessage(responseMessage);
            logger.info("result: {}", result);
            if(result > 0) {
                return responseMessage;
            }
            return null;

        }catch (Exception e){
            logger.error(e.getMessage());

            return null;
        }
    }

}
