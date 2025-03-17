package com.refactoring.finalproject.user.controller;

import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.dto.SubChatroomDto;
import com.refactoring.finalproject.user.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.List;


@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        logger.info("/user/login test");
        return "user/login";
    }

    @PostMapping("/login")
    public String postLogin(HttpSession session, @ModelAttribute LoginDto loginDto) {

        logger.info("loginDto1 : {}", loginDto);

        String username = loginDto.getIdValue();

        boolean userVerify = userService.getUserLoginVerifyByLoginDto(loginDto);

        if(!userVerify) {
            return "user/login";
        }
        session.setAttribute("user", username);
        session.setAttribute("login", true);
        return "redirect:/";

    }

    @GetMapping("/register")
    public String getRegister(Model model) {
        return "user/register";

    }

    @GetMapping("/logout")
    public String getLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

// 마이페이지
    @GetMapping("/mypage")
    public String getMypage(Model model) {

        return "/user/mypage";
    }

    // 유저가 구독중인 채팅방 및 입장 하기
    @GetMapping("/mypage/chat/list")
    public String getMypageChatList(Model model, HttpSession session) {
        String username = (String) session.getAttribute("user");
        List<SubChatroomDto> subChatroom = userService.getSubChatroomByUserName(username);

        model.addAttribute("subChatroom", subChatroom);
        return "/user/chat-list";
    }

}
