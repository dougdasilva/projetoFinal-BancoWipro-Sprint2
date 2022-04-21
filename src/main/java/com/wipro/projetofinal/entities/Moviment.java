package com.wipro.projetofinal.entities;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wipro.projetofinal.entities.enums.CardLevel;
import com.wipro.projetofinal.entities.enums.MovimentDescription;

@Entity
@Table(name = "tb_movimenties")
public class Moviment implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Calendar movimentDate;
	private Double value;
	private String accountDestination;
	private MovimentDescription movimentDescription;
	
	public Moviment() {
		// TODO Auto-generated constructor stub
	}

	public Moviment(Long id, Calendar movimentDate, Double value, String accountDestination, MovimentDescription md) {
		this.id = id;
		this.movimentDate = movimentDate;
		this.value = value;
		this.accountDestination = accountDestination;
		this.movimentDescription = md;
		setMovimentDescription(movimentDescription);
	}

	public Calendar getMovimentDate() {
		return movimentDate;
	}

	public void setMovimentDate(Calendar movimentDate) {
		this.movimentDate = movimentDate;
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

	public void setMovimentDescription(MovimentDescription moviment ){
		if(moviment!=null) {
			this.movimentDescription = MovimentDescription.valueOf(moviment.getCode());
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
