package com.refactoring.finalproject.user.controller;




import com.refactoring.finalproject.user.dto.LoginDto;
import com.refactoring.finalproject.user.service.UserService;

import oracle.ucp.proxy.annotation.Post;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;



@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LogManager.getLogger(this.getClass());

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginGet(Model model) {
        logger.info("/user/login test");
        return "user/login";
    }

    @PostMapping("/login")
    public String loginPost(HttpSession session, @ModelAttribute LoginDto loginDto) {

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
    public String register(Model model) {
        return "user/register";

    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }


}
