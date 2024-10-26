package com.refactoring.finalproject.chat.service;

import com.refactoring.finalproject.chat.dto.MessageRequest;
import com.refactoring.finalproject.chat.dto.ChatRoomDto;

import java.util.List;

public interface ChatService {

    /**
     * 채팅방 리스트 조회
     * @return 존재하는 채팅방 리스트
     */
    List<ChatRoomDto> getChatRoomList();


    /**
     * 채팅방 생성
     * @param chatroomName - 생성하려는 채팅방 제목
     * @param username - 채팅방을 생성하려는 유저
     */
    void createChatroom(String chatroomName, String username);

    /**
     * 채팅방 제목으로 채팅방 고유 NUMBER값 찾기
     * @param roomNo - 채팅방의 고유 NUMBER
     * @return 찾으려는 채팅방의 정보
     */
    ChatRoomDto getChatRoomByRoomId(Long roomNo);

    List<MessageRequest> getPreviousMessage(Long roomNo);
}
