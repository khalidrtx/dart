package com.example.demo.controllers.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.Collection;

import javax.servlet.http.HttpSession;

@Controller
public class RedirectController {

	@GetMapping("/redirectByRole")
	public String redirectByRole(Authentication authentication, Model model, HttpSession session) {
	    Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

	    if (hasRole(authorities, "Admin")) {
	        // Admin user, add details to the session and show Admin profile directly
	        session.setAttribute("userDetails", authentication.getPrincipal());
		    

	        return "Admin/Index";
	    } else if (hasRole(authorities, "User")) {
	        // Regular user, add details to the session and show User profile directly
	        session.setAttribute("userDetails", authentication.getPrincipal());
	        return "User/Index";
	    } else {
	        // Unknown role, redirect to default
	        return "redirect:/";
	    }
	}
	
	


    private boolean hasRole(Collection<? extends GrantedAuthority> authorities, String targetRole) {
        return authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .anyMatch(role -> role.equalsIgnoreCase(targetRole));
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/index")
    public String showIndexPage() {
        return "index";
    }

 
}
