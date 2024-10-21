package com.refactoring.finalproject.chat.dao;

import com.refactoring.finalproject.chat.dto.ChatRoomDto;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
@MapperScan
public interface ChatDao {
    List<ChatRoomDto> selectChatRoomList();

    void insertChatroom(ChatRoomDto chatRoomDto);

    Long selectUserNoByUsername();

    ChatRoomDto selectChatRoom(String roomNo);
}
