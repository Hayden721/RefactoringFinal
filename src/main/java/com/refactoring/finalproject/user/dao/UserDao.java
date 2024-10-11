package com.refactoring.finalproject.user.dao;


import com.refactoring.finalproject.user.dto.LoginDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


public interface UserDao {




    boolean selectUserDataByLoginDto(LoginDto loginDto);
}
