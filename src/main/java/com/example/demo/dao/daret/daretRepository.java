package com.example.demo.dao.daret;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import com.example.demo.entities.daret.daret;


@Repository
public interface daretRepository extends JpaRepository<daret, Long>{
	
}
