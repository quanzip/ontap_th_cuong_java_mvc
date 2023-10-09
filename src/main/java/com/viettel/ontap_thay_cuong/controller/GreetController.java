package com.viettel.ontap_thay_cuong.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(value = "/api/v1")
public class GreetController {
    @GetMapping(value = "/hello")
    public String sayHi(HttpSession session){
        session.setAttribute("name","Pham Hong Quan Zip");
//        return "redirect:/api/v1/login";
        return "hello/hi";
    }
}
