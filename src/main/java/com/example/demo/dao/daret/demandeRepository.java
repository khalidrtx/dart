package com.example.demo.dao.daret;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.daret.demande;

@Repository
public interface demandeRepository extends JpaRepository<demande, Long>{
	
	List<demande> findByUtilisateurId(Long id);
	
}
