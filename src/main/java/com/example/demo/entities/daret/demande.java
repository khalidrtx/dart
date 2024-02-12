package com.example.demo.entities.daret;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entities.security.Utilisateur;


@Entity
@Table
public class demande {
	
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	
	@ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;
	
	private String libelle;
	private int nbParticipant;
	private long montantPeriode;
	private String periodicite;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDepart;
	
	
	
	
	
	
	public String getLibelle() {
		return libelle;
	}
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNbParticipant() {
		return nbParticipant;
	}
	public void setNbParticipant(int nbParticipant) {
		this.nbParticipant = nbParticipant;
	}
	public long getMontantPeriode() {
		return montantPeriode;
	}
	public void setMontantPeriode(long montantPeriode) {
		this.montantPeriode = montantPeriode;
	}
	public String getPeriodicite() {
		return periodicite;
	}
	public void setPeriodicite(String periodicite) {
		this.periodicite = periodicite;
	}
	public Date getDateDepart() {
		return dateDepart;
	}
	public void setDateDepart(Date dateDepart) {
		this.dateDepart = dateDepart;
	}
	public Utilisateur getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}
	
	
	
	
	

}