package com.example.demo.entities.daret;


import javax.persistence.*;

import com.example.demo.entities.security.Utilisateur;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Position{
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "daret_id")
    private daret daret;

    @OneToMany(mappedBy = "position", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Payment> payments;
    private LocalDateTime dateParticipation;


    private double proposedAmount;
    private float nbParticipantOrdered;
    
    private Integer period; //  field  represent the period(tours) for each user


    public LocalDateTime getDateParticipation() {
		return dateParticipation;
	}

	public void setDateParticipation(LocalDateTime dateParticipation) {
		this.dateParticipation = dateParticipation;
	}

	public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

	public void setUtilisateur(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	public daret getDaret() {
		return daret;
	}

	public void setDaret(daret daret) {
		this.daret = daret;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public double getProposedAmount() {
		return proposedAmount;
	}

	public void setProposedAmount(double proposedAmount) {
		this.proposedAmount = proposedAmount;
	}

	public float getNbParticipantOrdered() {
		return nbParticipantOrdered;
	}

	public void setNbParticipantOrdered(float nbParticipantOrdered) {
		this.nbParticipantOrdered = nbParticipantOrdered;
	}
    
    
}