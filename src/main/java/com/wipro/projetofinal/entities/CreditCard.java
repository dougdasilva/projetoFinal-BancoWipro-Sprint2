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

@Entity
@Table(name = "tb_creditcard")
public class CreditCard implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String cardNumber;
	private Double creditLimit;
	private String cvv;
	private Calendar expirationDate;
	private String flag;
	private CardLevel cardLevel;

	private Boolean ativo;

	public CreditCard() {
		this.ativo = false;
	}

	public CreditCard(String cardNumber, String cvv, CardLevel level) {
		this.ativo = true;
		this.cardNumber = cardNumber;
		this.creditLimit = 300.0;
		this.cvv = cvv;
		this.expirationDate = Calendar.getInstance();
		this.expirationDate.add(Calendar.YEAR, 6); // A validade serï¿½ contada da data atual somado com mais 6 anos
		this.flag = "MASTERCARD";
		setCardLevel(level);
	}


	public String getCardNumber() {
		return cardNumber;
	}

	public Double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(Double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getCvv() {
		return cvv;
	}

	public Calendar getExpirationDate() {
		return expirationDate;
	}

	public String getFlag() {
		return flag;
	}

	public CardLevel getCardLevel() {
		return  this.cardLevel;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public Long getId() {
		return id;
	}

	public void setCardLevel(CardLevel cardLevel) {
		if(cardLevel!=null) {
			this.cardLevel = cardLevel;
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
		CreditCard other = (CreditCard) obj;
		return Objects.equals(id, other.id);
	}

}