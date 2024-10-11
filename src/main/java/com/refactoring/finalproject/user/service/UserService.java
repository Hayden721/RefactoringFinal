package com.refactoring.finalproject.user.service;


import com.refactoring.finalproject.user.dto.LoginDto;

public interface UserService {




    boolean getUserLoginVerifyByLoginDto(LoginDto loginDto);
}
