package com.wipro.projetofinal.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public abstract class User implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;
	
	@NotBlank(message = "Nome é obrigatório!")
	protected String name;
	
	@CPF(message = "CPF Inválido!")
    @Column(nullable = false, unique = true)
    private String cpf;
	
	@NotBlank(message = "Email é obrigatório!")
	@Email(message = "Email Inválido!")
	@Column(nullable = false, unique = true)
	protected String email;
	
	@NotBlank(message = "Senha é obrigatório!")
	@Size(min = 6, max = 50)
	protected String password;
	

	public User() {
	}

	public User(String name, String cpf, String email, String password) {
		this.name = name;
		this.cpf = cpf;
		this.email = email;
		this.password = password;
		setCpf(cpf);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}
	
	public void setCpf(String cpf) {
        if(cpf != null && cpf.length() == 0) {
            this.cpf = null;
        }
        else {
            this.cpf = cpf;
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}

}
