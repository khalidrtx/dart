package com.example.demo.controllers.daret;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entities.daret.daret;
import com.example.demo.entities.daret.Position;
import com.example.demo.services.daret.PositionService;
import com.example.demo.services.daret.daretService;

@RestController
@RequestMapping("/api/daret")
public class daretRestController {

    @Autowired
    private daretService daretService;
    @Autowired
    private PositionService positionService;
    
    @PostMapping("/savedaret")
    public ResponseEntity<?> savedaret(@RequestBody daret daret) {
        List<String> errors = new ArrayList<>();

        if (daret.getLibelle() == null || daret.getLibelle().isEmpty()) {
            errors.add("libelle est vide");
        }

        if (daret.getNbParticipant() < 1) {
            errors.add("nombre de participants invalide");
        }

        if (daret.getMontantPeriode() <= 0) {
            errors.add("montant par période invalide");
        }

        if (daret.getPeriodicite() == null || daret.getPeriodicite().isEmpty()) {
            errors.add("périodicité est vide");
        }

        if (daret.getDateDepart() == null) {
            errors.add("date de démarrage est null");
        } 


        if (!errors.isEmpty()) {
            // Return a response with error messages
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        daretService.savedaret(daret);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    
    @GetMapping("/all")
    public ResponseEntity<Iterable<daret>> getAllDarets() {
        Iterable<daret> darets = daretService.getAlldaret();
        return new ResponseEntity<>(darets, HttpStatus.OK);
    }
    
    @GetMapping("/detail/{daretId}")
    public ResponseEntity<?> getDaretDetail(@PathVariable long daretId) {
        List<Position> positions = positionService.getPositionsForDaret(daretId);

        return new ResponseEntity<>(positions, HttpStatus.OK);
    }
    @GetMapping("/{daretId}")
    public ResponseEntity<daret> getDaretById(@PathVariable Long daretId) {
        // Retrieve the 'daret' by ID
        daret selectedDaret = daretService.getDaretById(daretId);

        // Check if the 'daret' was found
        if (selectedDaret != null) {
            return new ResponseEntity<>(selectedDaret, HttpStatus.OK);
        } else {
            // 'daret' not found, return 404 Not Found status
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
   

    
}
