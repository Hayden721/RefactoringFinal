package com.refactoring.finalproject.interceptor;


import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginSessionInterceptor implements HandlerInterceptor {

    // 유저의 세션 체크
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getSession().getAttribute("login") == null) {

            request.getSession().setAttribute("loginError", true);

            response.sendRedirect("/user/login");

            return false;
        }

        return true;
    }


}
