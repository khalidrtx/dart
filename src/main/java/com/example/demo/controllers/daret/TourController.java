package com.example.demo.controllers.daret;

import com.example.demo.services.daret.PositionService;
import com.example.demo.services.daret.TourService;
import com.example.demo.entities.daret.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping("/api/tour")
public class TourController {

    @Autowired
    private TourService tourService;
	   @Autowired
	private PositionService positionService;
    @PostMapping("/shuffle/{id}")
    public ResponseEntity<Void> shuffleDaret(@PathVariable Long id) {
        tourService.shuffleDaret(id);
        return ResponseEntity.ok().build();
    }
    
    
    
    @GetMapping("/period/{userId}/{daretId}")
    public ResponseEntity<Integer> getPeriodByUtilisateurIdAndDaretId(@PathVariable Long userId, @PathVariable Long daretId) {
        // Assuming you have a service method to retrieve Position based on UtilisateurId and DaretId
        Optional<Position> optionalPosition = positionService.getPositionByUserIdAndDaretId(userId, daretId);

        // Check if Position exists
        if (optionalPosition.isPresent()) {
            Position position = optionalPosition.get();

            // Assuming you have a service method to retrieve the period information based on Position
            Integer period = positionService.getPeriodByPositionId(position.getId());
            return ResponseEntity.ok(period);
        } else {
            // Handle the case where Position does not exist
            return ResponseEntity.notFound().build();
        }
    }


}
