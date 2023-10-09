package com.viettel.ontap_thay_cuong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/api/v1")
public class LoginController {
    @GetMapping(value = "/showLogin")
    public String showLoginForm() {
        return "login/login";
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam(value = "name") String name
            , @RequestParam(value = "passw") String passw, HttpSession session) {
        if (name.equals("quan") && passw.equals("123")) {
            session.setAttribute("name", name);
            session.setAttribute("passw", passw);
            return "hello/afterLogin";
        }
        return "redirect:/api/v1/showLogin";
    }
}
