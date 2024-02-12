package com.example.demo.controllers.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.demo.dao.security.RoleRepository;
import com.example.demo.dao.security.UtilisateurRepository;
import com.example.demo.entities.security.Role;
import com.example.demo.entities.security.Utilisateur;
import com.example.demo.services.security.ProfilePictureService;

import org.springframework.web.multipart.MultipartFile;


@Controller
public class RegistrationController {

 private final UtilisateurRepository utilisateurRepository;
 private final RoleRepository roleRepository;
 private final PasswordEncoder passwordEncoder;
 private final ProfilePictureService profilePictureService; 
 @Autowired
 public RegistrationController(UtilisateurRepository utilisateurRepository, 
                               RoleRepository roleRepository, 
                               PasswordEncoder passwordEncoder,
                               ProfilePictureService profilePictureService) {
     this.utilisateurRepository = utilisateurRepository;
     this.roleRepository = roleRepository;
     this.passwordEncoder = passwordEncoder;
     this.profilePictureService = profilePictureService;
 }

	 @GetMapping("/register")
	 public String showRegistrationForm(Model model) {
	     model.addAttribute("user", new Utilisateur());
	     return "register";
	 }
 @PostMapping("/register")
 public String registerUser(@ModelAttribute("user") Utilisateur user,
                            @RequestParam("profilePicture") MultipartFile profilePicture) {

     // Validate and save the profile picture
     if (profilePicture != null && !profilePicture.isEmpty()) {
         try {
             String profilePicturePath = profilePictureService.saveProfilePicture(profilePicture);
             user.setPhoto(profilePicturePath);
         } catch (IOException e) {
             e.printStackTrace();
         }
     }

     user.setPassword(passwordEncoder.encode(user.getPassword()));

     // Retrieve the "USER" role from the database
     Role userRole = roleRepository.findByName("USER");
     if (userRole == null) {
         throw new RuntimeException("Role 'USER' not found in the database.");
     }

     // Assign the retrieved role to the user
     user.addRole(userRole);

     // Save the user to the database
     utilisateurRepository.save(user);

     return "redirect:/login";
 }
 
 @PostMapping("/addUser")
 public ResponseEntity<?> add(@ModelAttribute("user") Utilisateur user,
		 @RequestParam(value = "profilePicture", required = false) MultipartFile profilePicture,
         @RequestParam("role") String role) {
	 
	 if (profilePicture != null && !profilePicture.isEmpty()) {
	        try {
	            String profilePicturePath = profilePictureService.saveProfilePicture(profilePicture);
	            user.setPhoto(profilePicturePath);
	        } catch (IOException e) {
	            // Handle the exception (e.g., log error, show user-friendly message)
	            e.printStackTrace();
	            return new ResponseEntity<>("Error saving profile picture.", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 

	 List<String> errors = new ArrayList<>();

	    if (user.getNom() == null || user.getNom().isEmpty()) {
	        errors.add("Nom est vide");
	    }

	    if (user.getPrenom() == null || user.getPrenom().isEmpty()) {
	        errors.add("Prénom est vide");
	    }

	    if (profilePicture == null || profilePicture.isEmpty()) {
	        errors.add("Photo de profil non fournie");
	    }

	    if (user.getDatenaiss() == null) {
	        errors.add("Date de naissance est vide");
	    } 
	    
	    if (user.getUsername() == null || user.getUsername().isEmpty()) {
	        errors.add("Nom d'utilisateur est vide");
	    }

	    if (user.getPassword() == null || user.getPassword().isEmpty()) {
	        errors.add("Mot de passe est vide");
	    }
	    
	    if (!errors.isEmpty()) {
            // Return a response with error messages
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

     user.setPassword(passwordEncoder.encode(user.getPassword()));

     // Validate and set the user role
     if ("User".equals(role) || "Admin".equals(role)) {
         Role userRole = roleRepository.findByName(role);
         if (userRole == null) {
             throw new RuntimeException("Role not found in the database.");
         }
         user.addRole(userRole);
     } else {
         throw new RuntimeException("Invalid role provided in the form.");
     }

     // Save the user to the database
     utilisateurRepository.save(user);
     
     return new ResponseEntity<>(HttpStatus.OK);

 }
 
}
