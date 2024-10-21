package com.refactoring.finalproject.chat.dto;

import org.apache.ibatis.type.Alias;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


public class ChatRoomDto {

    private String chatroomNo;
    private String chatroomName;
    private String chatroomCode;
    private Long userNo;

    public ChatRoomDto() {
    }

    public ChatRoomDto(String chatroomNo, String chatroomName, String chatroomCode, Long userNo) {
        this.chatroomNo = chatroomNo;
        this.chatroomName = chatroomName;
        this.chatroomCode = chatroomCode;
        this.userNo = userNo;
    }

    public String getChatroomNo() {
        return chatroomNo;
    }

    public void setChatroomNo(String chatroomNo) {
        this.chatroomNo = chatroomNo;
    }

    public String getChatroomName() {
        return chatroomName;
    }

    public void setChatroomName(String chatroomName) {
        this.chatroomName = chatroomName;
    }

    public String getChatroomCode() {
        return chatroomCode;
    }

    public void setChatroomCode(String chatroomCode) {
        this.chatroomCode = chatroomCode;
    }

    public Long getUserNo() {
        return userNo;
    }

    public void setUserNo(Long userNo) {
        this.userNo = userNo;
    }
}
