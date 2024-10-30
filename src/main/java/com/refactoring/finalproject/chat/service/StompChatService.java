package com.refactoring.finalproject.chat.service;

import com.refactoring.finalproject.chat.dto.MessageDto;

import java.util.List;

public interface StompChatService {
    /**
     * 채팅방에 접속 기록 확인
     * @param username - 유저 아이디
     * @param roomNo - 방 번호
     * @return 유저 존재 true / 존재하지 않으면 false
     */
    boolean getUserPresenceByUserName(String username, Long roomNo);

    /**
     * 채팅방에 접속한 유저 저장
     * @param username - 채팅방에 입장한 유저
     * @param roomNo - 입장한 채팅방 NUMBER
     */
    void saveEnterUser(String username, Long roomNo);


    /**
     * 클라이언트가 보낸 메시지를 저장하고 채팅방에 전송한다.
     * @param requestMessage - 클라이언트가 보낸 메시지 정보
     * @return 클라이언트가 보낸 메시지 정보
     */
    MessageDto saveAndSendMessage(MessageDto requestMessage);

    void leaveChatroomByUsername(String username, Long roomNo);

    /**
     *  채팅방 유저들을 조회한다.
     * @param roomNo - 현재 채팅방 번호
     * @return 현재 채팅방에 존재하는 유저 리스트
     */
    List<String> getChatroomUsersByRoomNo(Long roomNo);
}
