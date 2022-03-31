package com.th.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.th.service.SecurityService;

@RestController
public class MainController {
	  @Autowired
	    private SecurityService securityService;
@GetMapping("")
public String hello()
{
return "hello my secure app";	
}
@GetMapping("/login")
public String login(Model model, String error, String logout) {
    if (securityService.isAuthenticated()) {
        return "redirect:/";
    }

    if (error != null)
        model.addAttribute("error", "Your username and password is invalid.");

    if (logout != null)
        model.addAttribute("message", "You have been logged out successfully.");

    return "login";
}
}
