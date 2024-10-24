package com.refactoring.finalproject.chat.service;

import com.refactoring.finalproject.chat.dto.ChatMessageDto;

public interface StompChatService {
    /**
     * 
     * @param username
     * @return
     */
    boolean getUserPresenceByUserName(String username);

    /**
     * 채팅방에 접속한 유저 저장
     * @param username - 채팅방에 입장한 유저
     * @param roomNo - 입장한 채팅방 NUMBER
     */
    void saveEnterUser(String username, Long roomNo);



    ChatMessageDto saveAndSendMessage(ChatMessageDto responseMessage);
}
