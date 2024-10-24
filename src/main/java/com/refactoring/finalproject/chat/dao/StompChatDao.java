package com.refactoring.finalproject.chat.dao;

import com.refactoring.finalproject.chat.dto.ChatMessageDto;

public interface StompChatDao {


    boolean selectConfirmEnterUser(String username, Long roomNo);


    int insertMessage(ChatMessageDto responseMessage);

    Long selectUserNoByUsername(String sender);
}
