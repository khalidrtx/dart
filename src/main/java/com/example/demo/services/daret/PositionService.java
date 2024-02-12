package com.example.demo.services.daret;

import com.example.demo.dao.daret.*;
import com.example.demo.entities.daret.daret;
import com.example.demo.entities.security.Utilisateur;
import com.example.demo.entities.daret.Position;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
public class PositionService {

    @Autowired
    private PositionRepository positionRepository;
    
    @Autowired
    private daretRepository daretRepository; // Assuming you have a repository for Daret

    public List<Position> getAllPositions() {
        return positionRepository.findAll();
    }
    
    public List<Position> getPositionsForDaret(long daretId) {
        return null; //positionRepository.findByPositionPKid_Daret_Id(daretId);
    }

    
    
    
    public boolean existsByUtilisateurIdAndDaretId(Long utilisateur, Long daret) {
        try {
            return positionRepository.existsByUtilisateurIdAndDaretId(utilisateur, daret);
        } catch (Exception e) {
            // Log or handle the exception
            throw new RuntimeException("Error checking position existence: " + e.getMessage(), e);
        }
    }
    

    @Transactional
    public Position savePosition(Position newPosition) {
        try {
            // Save the position
            Position savedPosition = positionRepository.save(newPosition);

         // Update nbParticipantEnCour in Daret based on nbParticipantOrdered
            updateNbParticipantEnCour(newPosition.getDaret().getId(), newPosition.getNbParticipantOrdered());       
            return savedPosition;
        } catch (Exception e) {
            // Log or handle the exception
            throw new RuntimeException("Error saving position: " + e.getMessage(), e);
        }
    }

    private void updateNbParticipantEnCour(Long daretId, float nbParticipantOrdered) {
        try {
            // Get the Daret entity
            daret daret = daretRepository.findById(daretId)
                    .orElseThrow(() -> new EntityNotFoundException("Daret not found with id: " + daretId));

            // Update nbParticipantEnCour based on nbParticipantOrdered
            daret.setNbParticipantEnCour(daret.getNbParticipantEnCour() + nbParticipantOrdered);

            // Check if nbParticipantEnCour is equal to nbParticipant
            if (daret.getNbParticipantEnCour() == daret.getNbParticipant()) {
                daret.setStatus("Full");
            }

            // Save the updated Daret entity
            daretRepository.save(daret);
        } catch (Exception e) {
            // Log or handle the exception
            throw new RuntimeException("Error updating nbParticipantEnCour: " + e.getMessage(), e);
        }
    }
    
    public Optional<Position> getPositionById(Long positionId) {
        return positionRepository.findById(positionId);
    }
    public List<Position> getPositionsByDaretId(Long daretId) {
        return positionRepository.getPositionsByDaretId(daretId);
    }
    public List<Position> getPositionsByDaretIdAndUtilisateurId(Long daretId, Long utilisateurId) {
        return positionRepository.findByDaretIdAndUtilisateurId(daretId, utilisateurId);
    }

    public List<Position> getPositionsByUtilisateurId(Long utilisateurId) {
        return positionRepository.findByUtilisateurId(utilisateurId);
    }
        
    public Optional<Position> getPositionByUserIdAndDaretId(Long userId, Long daretId) {
        return positionRepository.findByUtilisateurIdAndDaretId(userId,daretId);
    }
    //pour le tour de rols
    public List<Utilisateur> getUsersByDaretId(Long daretId) {
        return positionRepository.findUsersByDaretId(daretId);
    }
    
    //period dyal wa7d lposition
    public Integer getPeriodByPositionId(Long positionId) {
        // Assuming you have a repository for Position
        Optional<Position> positionOptional = positionRepository.findById(positionId);

        // If the Position exists, return the period; otherwise, return null
        return positionOptional.map(Position::getPeriod).orElse(null);
    }
}