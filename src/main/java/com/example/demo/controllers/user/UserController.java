package com.example.demo.controllers.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

   
    @GetMapping("/User/Index")
    public String Index() {
        return "User/Index";
    }
    @GetMapping("/MesDarets")
    public String mesDart() {
        return "User/MesDarets";
    }
    @GetMapping("/MesDemandes")
    public String MesDemandes() {
        return "User/MesDemandes";
    }
    @GetMapping("/daretDetail")
    public String showDaretDetail(@RequestParam(name = "id") Long daretId, Model model) {
        // Here, you can use the 'daretId' parameter as needed
        // For example, you can fetch additional data based on the ID from your database

        // Add the 'daretId' to the model so it can be accessed in the Thymeleaf template
        model.addAttribute("daretId", daretId);

        // Return the name of the Thymeleaf template
        return "user/daretDetail";
    }
}
