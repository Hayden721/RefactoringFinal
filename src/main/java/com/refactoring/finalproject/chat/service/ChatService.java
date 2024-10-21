package com.refactoring.finalproject.chat.service;

import com.refactoring.finalproject.chat.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {
    List<ChatRoomDto> getChatRoomList();

    void addChatroom(String chatroomName, String username);

    ChatRoomDto getChatRoomByRoomId(String roomNo);
}
