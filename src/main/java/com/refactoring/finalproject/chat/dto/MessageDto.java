package com.refactoring.finalproject.chat.dto;

import java.time.LocalDateTime;

public class MessageDto {
    private String messageContent;
    private String sender;
    private LocalDateTime messageTime;

    private Long messageSender;
    private Long chatroomNo;
    private MessageType type;

    public enum MessageType {
        CHAT, ENTER, EXIT
    }
    public MessageDto() {
    }

    public MessageDto(String messageContent, String sender, LocalDateTime messageTime, Long messageSender, Long chatroomNo) {
        this.messageContent = messageContent;
        this.sender = sender;
        this.messageTime = messageTime;
        this.messageSender = messageSender;
        this.chatroomNo = chatroomNo;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public LocalDateTime getMessageTime() {
        return messageTime;
    }

    public void setMessageTime(LocalDateTime messageTime) {
        this.messageTime = messageTime;
    }

    public Long getMessageSender() {
        return messageSender;
    }

    public void setMessageSender(Long messageSender) {
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
        return "MessageDto{" +
                "messageContent='" + messageContent + '\'' +
                ", sender='" + sender + '\'' +
                ", messageTime=" + messageTime +
                ", messageSender=" + messageSender +
                ", chatroomNo=" + chatroomNo +
                '}';
    }
}

