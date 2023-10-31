package com.project.todo.todoproject.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
@SessionAttributes("name")
public class WelcomeController {

    @GetMapping
    public String goToWelcomePage(ModelMap modelMap){
        modelMap.put("name", getLoggedAuthenticated());
        return "welcome";
    }

    private String getLoggedAuthenticated(){
        Authentication authentication = SecurityContextHolder
                .getContext().getAuthentication();
        return authentication.getName();
    }
}
