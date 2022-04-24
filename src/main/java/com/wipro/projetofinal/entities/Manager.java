package com.wipro.projetofinal.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "tb_managers")
public class Manager extends User {

	private static final long serialVersionUID = 1L;

	@NotBlank(message = "Matrícula é obrigatório!")
	@Column(nullable = false, unique = true)
	private String registration;

	public Manager() {
		// TODO Auto-generated constructor stub
	}

	public Manager(String name, String cpf, String email, String password, String registration) {
		super(name, cpf, email, password);
		this.registration = registration;
	}

	public String getRegistration() {
		return registration;
	}

	public void setRegistration(String registration) {
		this.registration = registration;
	}

}
