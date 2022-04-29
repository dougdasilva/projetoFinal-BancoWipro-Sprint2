package com.wipro.projetofinal.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_checkingaccounts")
public class CheckingAccount extends Account {

	private static final long serialVersionUID = 1L;
	
	public CheckingAccount() {
		super.status = true;
	}
	
}
