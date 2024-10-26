package com.refactoring.finalproject.chat.dao;

import com.refactoring.finalproject.chat.dto.MessageDto;
import org.apache.ibatis.annotations.Param;

public interface StompChatDao {


    boolean selectConfirmEnterUser(String username, Long roomNo);

    int insertMessage(MessageDto responseMessage);

    Long selectUserNoByUsername(String sender);


    boolean selectEnteredChatroomUserByUserNo(@Param("userNo") Long userNo, @Param("roomNo") Long roomNo);


    void insertEnterUser(@Param("userNo") Long userNo, @Param("roomNo") Long roomNo);
}
