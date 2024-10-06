package com.refactoring.finalproject.user.service.impl;

import com.refactoring.finalproject.user.dao.UserDao;
import com.refactoring.finalproject.user.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }



    @Override
    public String getTestData() {

        return userDao.selectTestData();
    }
}
