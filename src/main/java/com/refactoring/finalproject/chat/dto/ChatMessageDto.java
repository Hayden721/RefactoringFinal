package com.refactoring.finalproject.chat.dto;


import org.apache.ibatis.type.Alias;

import java.util.Date;


@Alias("ChatMessageDto")
public class ChatMessageDto {
    private Long chatroomNo;
    private String chatRoomId; // 채팅방 고유코드
    private String sender;
    private String chatMessage; // 메시지
    private Date messageDate; // 메시지 날짜


    public ChatMessageDto() {
    }

    public ChatMessageDto(Long chatroomNo, String chatRoomId, String sender, String chatMessage, Date messageDate) {
        this.chatroomNo = chatroomNo;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.chatMessage = chatMessage;
        this.messageDate = messageDate;
    }

    public Long getChatroomNo() {
        return chatroomNo;
    }

    public void setChatroomNo(Long chatroomNo) {
        this.chatroomNo = chatroomNo;
    }

    public String getChatRoomId() {
        return chatRoomId;
    }

    public void setChatRoomId(String chatRoomId) {
        this.chatRoomId = chatRoomId;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getChatMessage() {
        return chatMessage;
    }

    public void setChatMessage(String chatMessage) {
        this.chatMessage = chatMessage;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }
}
