package com.refactoring.finalproject.user.dto;

public class SubChatroomDto {

    private Long chatroomNo; // 방번호
    private String chatroomName; // 방 제목
    private String chatroomCode; // 방 코드

    public SubChatroomDto() {
    }

    public SubChatroomDto(Long chatroomNo, String chatroomName, String chatroomCode) {
        this.chatroomNo = chatroomNo;
        this.chatroomName = chatroomName;
        this.chatroomCode = chatroomCode;
    }

    public Long getChatroomNo() {
        return chatroomNo;
    }

    public void setChatroomNo(Long chatroomNo) {
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

    @Override
    public String toString() {
        return "SubChatroomDto{" +
                "chatroomNo=" + chatroomNo +
                ", chatroomName='" + chatroomName + '\'' +
                ", chatroomCode='" + chatroomCode + '\'' +
                '}';
    }
}
