package com.wipro.projetofinal.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.GenericGenerator;

@MappedSuperclass
public abstract class Account implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2",strategy = "uuid2")
	String id;

	protected String accountNumber;
	protected Double balance;
	
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui o user relacionado a Account no db.
    protected Customer customer;
    
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui todas as movimentacoes relacionadas a Account no db.
	protected List<Moviment> moviment;
	
	@JsonFormat(shape= JsonFormat.Shape.STRING,pattern = "dd-MM-yyyy HH:mm:ss",timezone = "GMT-3")
	protected Instant createdDate;


	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)//exclui o cartão relacionado a Account no db.
	@JoinColumn(name = "credit_id")
	protected CreditCard creditCard;

	public Account() {
	}

	public Account(Double balance) {
		this.balance = balance;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public String getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

	public List<Moviment> getMoviment() {
		return moviment;
	}

	public Instant getCreatedDate(){
		return  this.createdDate;
	}
	public void setCreatedDate(Instant momento){
		this.createdDate =  momento;
	}

	public void setAccountNumber(){
		this.accountNumber = randomNumberAccount() ;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}
	


	public void updateBalance(Double value) {
		this.balance += value;
	}

	public String randomNumberAccount() {
		String numberAccount = "";
		Random random = new Random();
			for (int j = 0; j < 15; j++) {
				numberAccount += Integer.toString(random.nextInt(10));
			}
		return numberAccount;
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
		Account other = (Account) obj;
		return Objects.equals(id, other.id);
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}
	
	
	
	

}