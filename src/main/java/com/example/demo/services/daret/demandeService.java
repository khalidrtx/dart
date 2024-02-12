package com.example.demo.services.daret;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.daret.demandeRepository;
import com.example.demo.entities.daret.Position;
import com.example.demo.entities.daret.demande;

@Service
public class demandeService {
	
	
	@Autowired
	private demandeRepository demanderepository;
	
	public List<demande> getAlldemande(){
		return demanderepository.findAll();
	}
	
	
	public void savedemande(demande demande){
		this.demanderepository.save(demande);
	}
	
	
	public void deletedemandeparid(Long id){
		this.demanderepository.deleteById(id);
	}
	
	
	public demande getDemandeById(Long id) {
		Optional<demande> optional = demanderepository.findById(id);
		demande demande = null;
		if(optional.isPresent()) {
			demande = optional.get();
		}
		else {
			throw new RuntimeException("Demande n'exist pas :: "+id);
		}
		return demande;
	}
	
	
	public List<demande> getDemandesByUtilisateurId(Long id) {
        return demanderepository.findByUtilisateurId(id);
    }
	

}
