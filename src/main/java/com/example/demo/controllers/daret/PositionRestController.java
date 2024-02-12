package com.example.demo.controllers.daret;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.entities.daret.*;


import com.example.demo.services.daret.PositionService;
@RestController
@RequestMapping("/api/position")
public class PositionRestController {
	   @Autowired
	    private PositionService positionService;
	   @GetMapping("/all")
	    public ResponseEntity<List<Position>> getAllPositions() {
	        List<Position> positions = positionService.getAllPositions();

	        if (positions.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(positions, HttpStatus.OK);
	        }
	    }
	   
	   @PostMapping("/create")
	   public ResponseEntity<Position> createPosition(@RequestBody Position newPosition) {
	       // Check if a position already exists for the specified user and daret
	       Long userId = newPosition.getUtilisateur().getId();  // Assuming getId() returns the user_id
	       Long daretId = newPosition.getDaret().getId();  // Assuming getId() returns the daret_id

	       if (positionService.existsByUtilisateurIdAndDaretId(userId, daretId)) {
	           // Handle the case where the user is already in a position for this daret
	           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	       }

	       // Set nbParticipantOrdered based on the value received in the request
	        float nbParticipantOrdered = newPosition.getNbParticipantOrdered();
	        newPosition.setNbParticipantOrdered(nbParticipantOrdered);

	        // If no existing position, proceed with creating a new position
	        Position savedPosition = positionService.savePosition(newPosition);	    
	       return new ResponseEntity<>(savedPosition, HttpStatus.CREATED);
	   }
	   
	   
	   @GetMapping("/byDaret/{daretId}")
	    public ResponseEntity<List<Position>> getPositionsByDaretId(@PathVariable Long daretId) {
	        List<Position> positions = positionService.getPositionsByDaretId(daretId);
	        return new ResponseEntity<>(positions, HttpStatus.OK);
	    }
	    @GetMapping("/byUser/{utilisateurId}")
	    public ResponseEntity<List<Position>> getPositionsByUtilisateurId(@PathVariable Long utilisateurId) {
	        
	        List<Position> positions = positionService.getPositionsByUtilisateurId(utilisateurId);

	        if (positions.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(positions, HttpStatus.OK);
	        }
	    }
	    
	    @GetMapping("/byDaretAndUser/{daretId}/{utilisateurId}")
	    public ResponseEntity<List<Position>> getPositionsByDaretIdAndUtilisateurId(
	            @PathVariable Long daretId,
	            @PathVariable Long utilisateurId) {
	        
	        List<Position> positions = positionService.getPositionsByDaretIdAndUtilisateurId(daretId, utilisateurId);

	        if (positions.isEmpty()) {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        } else {
	            return new ResponseEntity<>(positions, HttpStatus.OK);
	        }
	    }

}
