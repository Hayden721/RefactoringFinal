package com.refactoring.finalproject.user.service;


import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.dto.SubChatroomDto;

import java.util.List;

public interface UserService {




    boolean getUserLoginVerifyByLoginDto(LoginDto loginDto);

    List<SubChatroomDto> getSubChatroomByUserName(String username);
}
