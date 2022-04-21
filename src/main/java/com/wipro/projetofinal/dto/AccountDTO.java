package com.wipro.projetofinal.dto;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.Moviment;
import com.wipro.projetofinal.entities.User;

public class AccountDTO {

	
	protected String accountNumber;
	protected Double balance;
	
    protected Customer customer;
    
	protected List<Moviment> moviment;
	
	protected Instant createdDate;
	
	
	public AccountDTO(Account account) {
		this.accountNumber = account.getAccountNumber();
		this.balance = account.getBalance();
		this.customer =  (Customer) account.getCustomer();
		this.moviment = account.getMoviment();
//		this.createdDate = account.
	}


	public String getAccountNumber() {
		return accountNumber;
	}


	public Double getBalance() {
		return balance;
	}


	public Customer getCustomer() {
		return customer;
	}


	public List<Moviment> getMoviment() {
		return moviment;
	}


	public Instant getCreatedDate() {
		return createdDate;
	}
	
	

}
