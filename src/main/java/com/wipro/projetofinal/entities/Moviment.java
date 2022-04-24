package com.wipro.projetofinal.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wipro.projetofinal.entities.enums.MovimentDescription;

@Entity
@Table(name = "tb_movimenties")
public class Moviment implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss", timezone = "GMT-3")
	private Instant movimentDate;
	private Double value;
	private String accountOrigin;
	private String accountDestination;
	private MovimentDescription movimentDescription;

	public Moviment() {
		// TODO Auto-generated constructor stub
	}

	public Moviment(Double value, String accountOrigin, String accountDestination, MovimentDescription md) {
		this.movimentDate = Instant.now();
		this.value = value;
		this.accountOrigin = accountOrigin;
		this.accountDestination = accountDestination;
		this.movimentDescription = md;
	}

	public Moviment(Double value, String accountDestination, MovimentDescription md) {
		this.movimentDate = Instant.now();
		this.value = value;
		this.accountDestination = accountDestination;
		this.movimentDescription = md;
	}

	public String getAccountOrigin() {
		return accountOrigin;
	}

	public Instant getMovimentDate() {
		return movimentDate;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getAccountDestination() {
		return accountDestination;
	}

	public void setAccountDestination(String accountDestination) {
		this.accountDestination = accountDestination;
	}

	public MovimentDescription getMovimentDescription() {
		return movimentDescription;
	}

	public Long getId() {
		return id;
	}

	public void setMovimentDescription(MovimentDescription moviment) {
		if (moviment != null) {
			this.movimentDescription = moviment;
		}
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moviment other = (Moviment) obj;
		return Objects.equals(id, other.id);
	}

}
