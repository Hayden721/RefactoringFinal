package com.refactoring.finalproject.chat.dto;


import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;




public class ChatMessageDto {
    private Long chatroomNo;
    private String chatRoomId; // 채팅방 고유코드
    private String sender;
    private Long senderNo;
    private String chatMessage; // 메시지
    private LocalDateTime messageDate; // 메시지 날짜


    public ChatMessageDto() {
    }

    public ChatMessageDto(Long chatroomNo, String chatRoomId, String sender, Long senderNo,String chatMessage, LocalDateTime messageDate) {
        this.chatroomNo = chatroomNo;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.senderNo = senderNo;
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

    public LocalDateTime getMessageDate() {
        return messageDate;
    }

    public Long getSenderNo() {
        return senderNo;
    }

    public void setSenderNo(Long senderNo) {
        this.senderNo = senderNo;
    }

    public void setMessageDate(LocalDateTime messageDate) {
        this.messageDate = messageDate;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "chatroomNo=" + chatroomNo +
                ", chatRoomId='" + chatRoomId + '\'' +
                ", sender='" + sender + '\'' +
                ", senderNo=" + senderNo +
                ", chatMessage='" + chatMessage + '\'' +
                ", messageDate=" + messageDate +
                '}';
    }
}
