package com.wipro.projetofinal.entities;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.Valid;

@Entity
@Table(name = "tb_customers")
public class Customer extends User{
	
	private static final long serialVersionUID = 1L;

	@Valid
	public Customer() {
	}

}
