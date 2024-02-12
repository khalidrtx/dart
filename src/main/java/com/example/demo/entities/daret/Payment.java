package com.example.demo.entities.daret;



import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "position_id")
    private Position position;

    private double amountPaid;
    private int period;
    private LocalDateTime dateLimite;
    private LocalDateTime datePayment; // Add the new attribute
    private String status;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Position getParticipation() {
		return position;
	}
	public void setParticipation(Position position) {
		this.position = position;
	}
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public LocalDateTime getDateLimite() {
		return dateLimite;
	}
	public void setDateLimite(LocalDateTime dateLimite) {
		this.dateLimite = dateLimite;
	}
	public LocalDateTime getDatePayment() {
		return datePayment;
	}
	public void setDatePayment(LocalDateTime datePayment) {
		this.datePayment = datePayment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Position getPosition() {
		return position;
	}
	public void setPosition(Position position) {
		this.position = position;
	}
	


}