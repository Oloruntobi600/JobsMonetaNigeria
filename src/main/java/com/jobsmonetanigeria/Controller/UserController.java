package com.jobsmonetanigeria.Controller;

import com.jobsmonetanigeria.Model.Users;
import com.jobsmonetanigeria.Service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.Table;

@Controller
@RequiredArgsConstructor
@Table
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String getLoginPage() {
        return "login_page";
    }

    @GetMapping("/register.html")
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new Users());
        return "register";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute Users user) {
        userService.register(user);
        return "redirect:/login?success";
    }

}
