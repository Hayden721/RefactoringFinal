package com.refactoring.finalproject.dto;

public class LoginDto {

    private String idValue;
    private String pwValue;

    public LoginDto() {
    }

    // 생성자
    public LoginDto(String idValue, String pwValue) {
        this.idValue = idValue;
        this.pwValue = pwValue;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public String getPwValue() {
        return pwValue;
    }

    public void setPwValue(String pwValue) {
        this.pwValue = pwValue;
    }

    @Override
    public String toString() {
        return "LoginDto{" +
                "idValue='" + idValue + '\'' +
                ", pwValue='" + pwValue + '\'' +
                '}';
    }
}
