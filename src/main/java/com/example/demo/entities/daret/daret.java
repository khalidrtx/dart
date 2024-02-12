package com.example.demo.entities.daret;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
@Table
public class daret{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String libelle;
	private int nbParticipant;
	private float nbParticipantEnCour;
	private long montantPeriode;
	private long montantTotal;
	private String periodicite;
	private int nbPeriode;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date dateDepart;
	private String status;
	@OneToMany(mappedBy = "daret", cascade = CascadeType.ALL)
	private List<Position> participants;


    @PrePersist
    @PreUpdate
    public void setLibelleAndCheckStatus() {
      //  this.libelle = "daret_" + this.id + "_" + this.montantPeriode;
        this.status="En cours";
        if(this.nbParticipant==this.nbParticipantEnCour) {
        	this.status="Demarer";
        }
    }
	

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public long getMontantTotal() {
		return montantTotal;
	}

	 public void setMontantPeriode(long montantPeriode) {
	        this.montantPeriode = montantPeriode;
	        updateMontantTotal();
	    }

	    public void setNbParticipant(int nbParticipant) {
	        this.nbParticipant = nbParticipant;
	        updateMontantTotal();
	    }

	    private void updateMontantTotal() {
	        this.montantTotal = montantPeriode * nbParticipant;
	        this.nbPeriode=nbParticipant;
	    }
	    
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public float getNbParticipantEnCour() {
			return nbParticipantEnCour;
		}
		public void setNbParticipantEnCour(float nbParticipantEnCour) {
			this.nbParticipantEnCour = nbParticipantEnCour;
		}
	
		public String getPeriodicite() {
			return periodicite;
		}
		public void setPeriodicite(String periodicite) {
			this.periodicite = periodicite;
		}
		public int getNbPeriode() {
			return nbPeriode;
		}
		public void setNbPeriode(int nbPeriode) {
			this.nbPeriode = nbPeriode;
		}
		public Date getDateDepart() {
			return dateDepart;
		}
		public void setDateDepart(Date dateDepart) {
			this.dateDepart = dateDepart;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getLibelle() {
			return libelle;
		}
		public int getNbParticipant() {
			return nbParticipant;
		}
		public long getMontantPeriode() {
			return montantPeriode;
		}
		public void setMontantTotal(long montantTotal) {
			this.montantTotal = montantTotal;
		}

		@JsonIgnore
		public List<Position> getParticipants() {
			return participants;
		}

		public void setParticipants(List<Position> participants) {
			this.participants = participants;
		}	
		
		
}
