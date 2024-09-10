package com.refactoring.finalproject.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {

    @GetMapping("/test")
    public String getTest() {
        return "/member/test";
    }
}
