package com.refactoring.finalproject.user.service.impl;


import com.refactoring.finalproject.user.dao.UserDao;
import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.service.UserService;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

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
}
