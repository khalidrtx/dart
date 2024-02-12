package com.example.demo.services.daret;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.daret.daret;
import com.example.demo.dao.daret.daretRepository;


@Service
public class daretService{
	
	@Autowired
	private daretRepository daretrepository;
	
	
	
	public List<daret> getAlldaret(){
		return daretrepository.findAll();
	}
	
	
	public void savedaret(daret daret){
		this.daretrepository.save(daret);
	}
	
	public void deletedaretparid(Long id){
		this.daretrepository.deleteById(id);
	}
	
	public daret getDaretById(Long id) {
		Optional<daret> optional = daretrepository.findById(id);
		daret daret = null;
		if(optional.isPresent()) {
			daret = optional.get();
		}
		else {
			throw new RuntimeException("Daret n'exist pas :: "+id);
		}
		return daret;
	}
}
