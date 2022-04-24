package com.wipro.projetofinal.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_specialaccounts")
public class SpecialAccount extends Account {

	private static final long serialVersionUID = 1L;

	private Double specialLimit;

	public SpecialAccount() {
	}

	public SpecialAccount(Double balance,Double specialLimit) {
		super(balance);
		this.specialLimit = specialLimit;
	}

	public Double getSpecialLimit() {
		return specialLimit;
	}

	public void setSpecialLimit(Double specialLimit) {
		this.specialLimit = specialLimit;
	}

}
