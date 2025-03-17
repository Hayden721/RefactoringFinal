package com.refactoring.finalproject.user.dao;


import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.dto.SubChatroomDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface UserDao {

    boolean selectUserDataByLoginDto(LoginDto loginDto);

    Long selectUserNoByUsername(String username);

    List<SubChatroomDto> selectSubChatroomByUserNo(Long userNo);
}
