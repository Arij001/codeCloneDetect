package com.jianghao.codeclonedetect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorController implements org.springframework.boot.web.servlet.error.ErrorController {
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        Exception exception = (Exception) request.getAttribute("javax.servlet.error.exception");
        String message = (String) request.getAttribute("javax.servlet.error.message");
        String requestUri = (String) request.getAttribute("javax.servlet.error.request_uri");

        model.addAttribute("statusCode", statusCode);
        model.addAttribute("exception", exception);
        model.addAttribute("message", message);
        model.addAttribute("requestUri", requestUri);

        return "error";
    }

//    @Override
    public String getErrorPath() {
        return "/error";
    }
}

