package com.refactoring.finalproject.chat.dto;



public class SaveChatUserDto {
    private Long chatroomUserNo;
    private Long chatroomNo;
    private Long chatUserNo;


    public SaveChatUserDto() {

    }

    public SaveChatUserDto(Long chatroomUserNo, Long chatroomNo, Long chatUserNo) {
        this.chatroomUserNo = chatroomUserNo;
        this.chatroomNo = chatroomNo;
        this.chatUserNo = chatUserNo;
    }

    public Long getChatroomUserNo() {
        return chatroomUserNo;
    }

    public void setChatroomUserNo(Long chatroomUserNo) {
        this.chatroomUserNo = chatroomUserNo;
    }

    public Long getChatroomNo() {
        return chatroomNo;
    }

    public void setChatroomNo(Long chatroomNo) {
        this.chatroomNo = chatroomNo;
    }

    public Long getChatUserNo() {
        return chatUserNo;
    }

    public void setChatUserNo(Long chatUserNo) {
        this.chatUserNo = chatUserNo;
    }
}
