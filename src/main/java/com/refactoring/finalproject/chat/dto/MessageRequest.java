package com.refactoring.finalproject.chat.dto;


import java.time.LocalDateTime;




public class MessageRequest {

    private String messageContent; // 메시지
    private LocalDateTime messageTime; // 메시지 날짜
    private char messageDelete;

    // foreign key
    private String messageSender;
    private Long chatroomNo;

    public MessageRequest() {
    }

    public MessageRequest(String messageContent, LocalDateTime messageTime, char messageDelete, String messageSender, Long chatroomNo) {
        this.messageContent = messageContent;
        this.messageTime = messageTime;
        this.messageDelete = messageDelete;
        this.messageSender = messageSender;
        this.chatroomNo = chatroomNo;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public char getMessageDelete() {
        return messageDelete;
    }

    public void setMessageDelete(char messageDelete) {
        this.messageDelete = messageDelete;
    }

    public String getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(String messageSender) {
        this.messageSender = messageSender;
    }

    public Long getChatroomNo() {
        return chatroomNo;
    }

    public void setChatroomNo(Long chatroomNo) {
        this.chatroomNo = chatroomNo;
    }

    @Override
    public String toString() {
        return "ChatMessageDto{" +
                "messageContent='" + messageContent + '\'' +
                ", messageTime=" + messageTime +
                ", messageDelete=" + messageDelete +
                ", messageSender='" + messageSender + '\'' +
                ", chatroomNo=" + chatroomNo +
                '}';
    }
}
