package com.example.demo.services.daret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daret.PositionRepository;
import com.example.demo.dao.daret.daretRepository;
import com.example.demo.entities.daret.Position;
import com.example.demo.entities.daret.daret;

@Service
public class TourService {

    @Autowired
    private PositionRepository positionRepository; 
    @Autowired
    private daretRepository daretRepository; 
    
    public void shuffleDaret(Long daretId) {
        // Fetch the Daret by ID
        daret daret = daretRepository.findById(daretId)
                .orElseThrow(() -> new RuntimeException("Daret not found for ID: " + daretId));

        // Add logic to shuffle Daret positions
        assignRandomPeriods(daret);

        // Save the updated Daret with shuffled positions
        daretRepository.save(daret);
    }
    
    public void assignRandomPeriods(daret daret) {
        // Liste des périodes disponibles initialement
        List<Integer> availablePeriods = new ArrayList<>();
        for (int i = 1; i <= daret.getNbPeriode(); i++) {
            availablePeriods.add(i);
        }

        // Liste des positions à assigner
        List<Position> positions = daret.getParticipants();
        // Mélanger les périodes disponibles de manière aléatoire
        Collections.shuffle(availablePeriods);

        // Garder une trace des numéros de période déjà utilisés
        Set<Float> usedPeriods = new HashSet<>();

        // Liste pour stocker les positions avec le même numéro de période
        List<Position> samePeriodPositions = new ArrayList<>();

        // Pour chaque position à assigner
        for (Position position : positions) {
            if (!availablePeriods.isEmpty()) {
                float randomPeriod = 0;

                // Si nbParticipantOrdered est égal à 0.5, ajouter la position à la liste
                if (position.getNbParticipantOrdered() == 0.5) {
                    samePeriodPositions.add(position);
                  } else if (position.getNbParticipantOrdered() > 1) {
                    do {
                        // Utiliser la période aléatoire et ajouter nbParticipantOrdered
                        randomPeriod =  availablePeriods.remove(0) + position.getNbParticipantOrdered() - 1;
                    } while (usedPeriods.contains(randomPeriod) || randomPeriod > daret.getNbPeriode());
                }  else {
                    // Sinon, attribuer une période aléatoire
                    do {
                        randomPeriod = availablePeriods.remove(0);
                    } while (usedPeriods.contains(randomPeriod) || randomPeriod > daret.getNbPeriode());
                }

                // Marquer la période comme utilisée
                usedPeriods.add(randomPeriod);

                // Assigner la période à la position
                position.setPeriod((int) randomPeriod);
            } else {
                // ... (existing code for handling the case when availablePeriods is empty)
            }
        }

        // Assigner la même période aux positions avec nbParticipantOrdered == 0.5
        if (!samePeriodPositions.isEmpty()) {
            float randomPeriod;
            do {
                randomPeriod = availablePeriods.remove(0);
            } while (usedPeriods.contains(randomPeriod) || randomPeriod > daret.getNbPeriode());

            int samePeriod = (int) randomPeriod;
            for (Position p : samePeriodPositions) {
                p.setPeriod(samePeriod);
            }
        }
    }
}
