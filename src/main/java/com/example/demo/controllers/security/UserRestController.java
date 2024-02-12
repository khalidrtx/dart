package com.example.demo.controllers.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entities.daret.daret;
import com.example.demo.entities.security.Utilisateur;
import com.example.demo.services.security.CustomUserDetailsService;

@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @GetMapping("/{userId}")
    public ResponseEntity<Utilisateur> getUserById(@PathVariable Long userId) {
        Utilisateur user = customUserDetailsService.loadUserById(userId);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/all")
    public ResponseEntity<Iterable<Utilisateur>> getAllUtilisateurs() {
        Iterable<Utilisateur> Utilisateurs = customUserDetailsService.getAllUtilisateur();
        return new ResponseEntity<>(Utilisateurs, HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
    	customUserDetailsService.deleteUtilisateurparid(id);
    }

}