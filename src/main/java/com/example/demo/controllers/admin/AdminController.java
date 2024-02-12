package com.example.demo.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

   
    @GetMapping("/Admin/Index")
    public String Index() {
        return "admin/Index";
    }
    @GetMapping("/Demandes")
    public String Demandes() {
        return "admin/Demandes";
    }
    @GetMapping("/Users")
    public String Users() {
        return "admin/Users";
    }
    @GetMapping("/daretsDetail")
    public String showDaretDetail(@RequestParam(name = "id") Long daretId, Model model) {
        // Here, you can use the 'daretId' parameter as needed
        // For example, you can fetch additional data based on the ID from your database

        // Add the 'daretId' to the model so it can be accessed in the Thymeleaf template
        model.addAttribute("daretId", daretId);

        // Return the name of the Thymeleaf template
        return "admin/daretsDetail";
    }

}
