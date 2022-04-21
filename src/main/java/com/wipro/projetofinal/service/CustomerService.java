package com.wipro.projetofinal.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.projetofinal.entities.Account;
import com.wipro.projetofinal.entities.CheckingAccount;
import com.wipro.projetofinal.entities.Customer;
import com.wipro.projetofinal.entities.SpecialAccount;
import com.wipro.projetofinal.repository.CheckingAccountRepository;
import com.wipro.projetofinal.repository.CustomerRepository;
import com.wipro.projetofinal.repository.SpecialAccountRepository;
import com.wipro.projetofinal.service.exeption.NumberException;
import com.wipro.projetofinal.service.exeption.ResourceNotFoundExcception;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository repository;

	@Autowired
	private CheckingAccountRepository checkingAccountRepository;

	@Autowired
	private SpecialAccountRepository specialAccountRepository;

	public Account getAccount(String numberAccount) {
		Account account = checkingAccountRepository.findByAccountNumber(numberAccount);
		if (account != null) {
			return account;
		} else {
			account = specialAccountRepository.findByAccountNumber(numberAccount);
			Optional<Account> special = Optional.ofNullable(account);
			return special.orElseThrow(() -> new ResourceNotFoundExcception(numberAccount));
		}
	}

	
	public Account deposit(String numberAccount, Double value) {
		Account account = getAccount(numberAccount);
		if(value > 0) {
			System.out.println(value);
			account.updateBalance(value);
			System.out.println(account.getClass().getName());
			if(account.getClass().getName().equals("com.wipro.projetofinal.entities.CheckingAccount")) {
				 checkingAccountRepository.save((CheckingAccount) account);
				 return account;
			}
			if(account.getClass().getName().equals("com.wipro.projetofinal.entities.SpecialAccount")) {
				specialAccountRepository.save((SpecialAccount) account);
				 return account;
			}
		}
		//return account;
		//Optional<Account> nvalue = Optional.ofNullable(account);
		throw new NumberException(value);		
	}
}
