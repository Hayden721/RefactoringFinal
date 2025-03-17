package com.refactoring.finalproject.user.service.impl;


import com.refactoring.finalproject.user.dao.UserDao;
import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.dto.SubChatroomDto;
import com.refactoring.finalproject.user.service.UserService;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final Logger logger = LogManager.getLogger(this.getClass());

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public boolean getUserLoginVerifyByLoginDto(LoginDto loginDto) {

        return userDao.selectUserDataByLoginDto(loginDto);
    }

    @Override
    public List<SubChatroomDto> getSubChatroomByUserName(String username) {

        Long userNo = userDao.selectUserNoByUsername(username);
        return userDao.selectSubChatroomByUserNo(userNo);
    }
}
