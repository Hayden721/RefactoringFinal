package com.refactoring.finalproject.chat.service.impl;

import com.refactoring.finalproject.chat.dao.StompChatDao;
import com.refactoring.finalproject.chat.dto.MessageDto;
import com.refactoring.finalproject.chat.service.StompChatService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StompChatImpl implements StompChatService {

    private static final Logger logger = LoggerFactory.getLogger(StompChatImpl.class);
    private final StompChatDao stompChatDao;

    public StompChatImpl(StompChatDao stompChatDao) {
        this.stompChatDao = stompChatDao;
    }

    /**
     * 채팅방에 존재하는 유저들을 반환합니다.
     *
     * @param username - 사용자 ID
     * @param roomNo - 채팅방 번호
     * @return
     */
    @Override
    public boolean getUserPresenceByUserName(String username, Long roomNo) {

        Long userNo = stompChatDao.selectUserNoByUsername(username);
        logger.info("selectUserNoByUsername: {}", userNo);
        return stompChatDao.selectEnteredChatroomUserByUserNo(userNo, roomNo);
    }

    @Override
    public void saveEnterUser(String username, Long roomNo) {
        Long userNo = stompChatDao.selectUserNoByUsername(username);
        stompChatDao.insertEnterUser(userNo, roomNo);
    }

    @Override
    public MessageDto saveAndSendMessage(MessageDto responseMessage) {
        logger.info("responseMessage: {}", responseMessage);
        LocalDateTime now = LocalDateTime.now();

        try {
            // 날짜 추가
            responseMessage.setMessageTime(now);
            // userNo 추가...
            responseMessage.setMessageSender(stompChatDao.selectUserNoByUsername(responseMessage.getSender()));

            logger.info("memberNo 찾기 : {}", stompChatDao.selectUserNoByUsername(responseMessage.getSender()));;

            logger.info("set 한거 확인 : {}", responseMessage);

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

    @Override
    public void leaveChatroomByUsername(String username, Long roomNo) {
        Long userNo = stompChatDao.selectUserNoByUsername(username);

        stompChatDao.deleteUserFromChatroom(userNo, roomNo);
    }

    @Override
    public List<String> getChatroomUsersByRoomNo(Long roomNo) {
        logger.info("채팅방 유저 리스트 : {}", stompChatDao.selectChatroomUsersByRoomNo(roomNo));
        return stompChatDao.selectChatroomUsersByRoomNo(roomNo);
    }

}
