package com.p92group.zhlobo.controllers;

import com.p92group.zhlobo.models.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    @GetMapping("/login/redirect")
    public String postLogin(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        var userRole = (UserRole)(authentication.getAuthorities().toArray()[0]);

        switch (userRole){
            case ROLE_CUSTOMER -> {
                return "redirect:/client";
            }
            case ROLE_MODERATOR -> {
                return "redirect:/moderator";
            }
            case ROLE_SELLER -> {
                return "redirect:/seller";
            }
        }

        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login"; // Assuming you have a login page named "login.html"
    }

    @GetMapping({"/logout"/*, "/login?logout"*/})
    public String logout() {
        return "redirect:/login?logout"; // Redirect to the login page after logout
    }
}
