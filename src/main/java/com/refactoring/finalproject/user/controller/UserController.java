package com.refactoring.finalproject.user.controller;



import com.refactoring.finalproject.dto.LoginDto;
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
    public String loginPost(HttpSession session, @ModelAttribute LoginDto loginDto, @RequestParam("idValue") String id, @RequestParam("pwValue") String pw) {
//        session.setAttribute("username", session.getAttribute());
        logger.info("loginDto1 : {}", loginDto.getIdValue());
        logger.info("loginDto2 : {}", loginDto.getPwValue());

        logger.info("login1 : {}", id);
        logger.info("login2 : {}", pw);


        logger.info("왜 안돼");
        return "redirect:/";
    }

    @GetMapping("/test")
    public String getTest(Model model) {
        logger.info("/test get!!");

        String testData = userService.getTestData();
        model.addAttribute("data", testData);
        return "/member/test";
    }


    @GetMapping("/register")
    public String register(Model model) {
        return "";
    }

    @GetMapping("/logout")
    public String logout() {
        return "";
    }
}
