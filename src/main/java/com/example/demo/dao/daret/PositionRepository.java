package com.example.demo.dao.daret;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.daret.Payment;
import com.example.demo.entities.daret.Position;
import com.example.demo.entities.security.Utilisateur;


@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
    
    boolean existsByUtilisateurIdAndDaretId(Long utilisateur, Long daret);
    List<Position> getPositionsByDaretId(Long daretId);
    List<Position> findByDaretIdAndUtilisateurId(Long daretId, Long utilisateurId);
    List<Position> findByUtilisateurId(Long utilisateurId);
    
    Optional<Position> findByUtilisateurIdAndDaretId(Long utilisateurId, Long daretId);
    
    //les utilisateurs qui sont dans la meme daret
    @Query("SELECT DISTINCT p.utilisateur FROM Position p JOIN p.daret d WHERE d.id = :daretId")
    List<Utilisateur> findUsersByDaretId(Long daretId);

}
