package com.example.demo.controllers.daret;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.security.Utilisateur;
import com.example.demo.entities.daret.Position;
import com.example.demo.entities.daret.demande;
import com.example.demo.services.daret.demandeService;

@RestController
@RequestMapping("/api/demande")
public class demandeRestController {
	
    @Autowired
    private demandeService demandeService;

    
    @PostMapping("/savedemande")
    public ResponseEntity<?> savedemande(@RequestBody demande demande) {
        List<String> errors = new ArrayList<>();

        if (demande.getLibelle() == null || demande.getLibelle().isEmpty()) {
            errors.add("libelle est vide");
        }

        if (demande.getNbParticipant() < 1) {
            errors.add("nombre de participants invalide");
        }

        if (demande.getMontantPeriode() <= 0) {
            errors.add("montant par période invalide");
        }

        if (demande.getPeriodicite() == null || demande.getPeriodicite().isEmpty()) {
            errors.add("périodicité est vide");
        }

        if (demande.getDateDepart() == null) {
            errors.add("date de démarrage est null");
        } else {
            // Add additional date validation if needed
        }
        
        Long userId = demande.getUtilisateur().getId();

        // Set the Utilisateur object with the user ID
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(userId);
        demande.setUtilisateur(utilisateur);

        // Add other field validations...

        if (!errors.isEmpty()) {
            // Return a response with error messages
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        demandeService.savedemande(demande);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @GetMapping("/all")
    public ResponseEntity<Iterable<demande>> getAlldemandes() {
        Iterable<demande> demandes = demandeService.getAlldemande();
        return new ResponseEntity<>(demandes, HttpStatus.OK);
    }
    
    
    @DeleteMapping("/delete/{id}")
    public void deleteDemande(@PathVariable Long id) {
            demandeService.deletedemandeparid(id);
    }
    
    
    @GetMapping("/byUser/{id}")
    public ResponseEntity<List<demande>> getDemandeByUtilisateurId(@PathVariable Long id) {
        
        List<demande> Demandes = demandeService.getDemandesByUtilisateurId(id);

        if (Demandes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(Demandes, HttpStatus.OK);
        }
    }

}
